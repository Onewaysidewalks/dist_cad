package ninja.onewaysidewalks.distcad.data.job.inmemory;

import com.google.inject.Binder;
import com.google.inject.Module;
import ninja.onewaysidewalks.distcad.data.job.core.JobReader;
import ninja.onewaysidewalks.distcad.data.job.core.JobWriter;

public class DataModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(JobReader.class).toInstance(new InMemoryJobRepository.InMemoryJobReader());
        binder.bind(JobWriter.class).toInstance(new InMemoryJobRepository.InMemoryJobWriter());
    }
}
