package pl.damianlegutko.demo.api.test.crud.domain.custom;

import com.google.inject.Inject;
import pl.damianlegutko.demo.api.test.crud.framework.config.Config;
import pl.damianlegutko.demo.api.test.crud.framework.retrofit.RetrofitFactory;
import pl.damianlegutko.demo.api.test.crud.framework.service.BaseService;

public class CustomService extends BaseService {
    final CustomApi api;

    @Inject
    public CustomService(Config config, RetrofitFactory retrofitFactory) {
        super(config, retrofitFactory);

        api = getApiInstance(CustomApi.class);
    }

    @Override
    public String getServiceName() {
        return "MyCustomService";
    }
}
