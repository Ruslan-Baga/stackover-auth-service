package stackover.auth.service.feign;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@FeignClient(name = "STACKOVER-PROFILE-SERVICE", fallbackFactory = ProfileServiceClient.ProfilesClientFallbackFactory.class)
public interface ProfileServiceClient {


    @Component
    class ProfilesClientFallbackFactory implements FallbackFactory<FallbackWithFactory> {
        @Override
        public FallbackWithFactory create(Throwable cause) {
            return new FallbackWithFactory(cause.getMessage());
        }
    }

    record FallbackWithFactory(String reason) implements ProfileServiceClient {

    }
}