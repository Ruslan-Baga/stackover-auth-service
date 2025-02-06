package stackover.auth.service.feign;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import stackover.auth.service.dto.profile.ProfilePostDto;

@FeignClient(name = "STACKOVER-PROFILE-SERVICE", fallbackFactory = ProfileServiceClient.ProfilesClientFallbackFactory.class)
public interface ProfileServiceClient {


    @PostMapping("/api/inner/profile")
    ResponseEntity<Void> createProfile(@RequestBody ProfilePostDto profilePostDto);


    @Component
    class ProfilesClientFallbackFactory implements FallbackFactory<FallbackWithFactory> {
        @Override
        public FallbackWithFactory create(Throwable cause) {
            return new FallbackWithFactory(cause.getMessage());
        }
    }

    record FallbackWithFactory(String reason) implements ProfileServiceClient {

        @Override
        public ResponseEntity<Void> createProfile(ProfilePostDto profilePostDto) {
            return ResponseEntity.status(500).build();
        }
    }
}