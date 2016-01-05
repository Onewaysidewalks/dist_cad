package ninja.onewaysidewalks.distcad.core.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
public class AutoConfigModules {
    private Reflections reflections;

    public AutoConfigModules(String[] packages) {
        ConfigurationBuilder cfgBldr = new ConfigurationBuilder();
        FilterBuilder filterBuilder = new FilterBuilder();
        for (String basePkg : packages) {
            cfgBldr.addUrls(ClasspathHelper.forPackage(basePkg));
            filterBuilder.include(FilterBuilder.prefix(basePkg));
            filterBuilder.excludePackage(getClass().getPackage().getName()); //we dont want to auto include anything in this core library
        }

        cfgBldr.filterInputsBy(filterBuilder).setScanners(
                new SubTypesScanner(), new TypeAnnotationsScanner());
        this.reflections = new Reflections(cfgBldr);
    }

    public static AutoConfigModules withPackages(List<String> packages) {
        return new AutoConfigModules(packages.toArray(new String[packages.size()]));
    }

    public List<Module> list() {
        final List<Module> modules = new ArrayList<>();

        registerModules(modules, reflections.getSubTypesOf(Module.class));

        return modules;
    }

    public void registerModules(List<Module> modules,  Set<Class<? extends Module>> modulesToRegister) {
        modulesToRegister.forEach(aClass -> {
            try {

                log.info("Auto configuring {}", aClass);
                Module module = aClass.newInstance();

                modules.add(module);
            } catch (InstantiationException | IllegalAccessException e) {
                log.error("Unable to autoconfigure module {}", aClass, e);
            }
        });
    }
}
