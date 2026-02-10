@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public Map<String, Object> me(Authentication authentication) {
        return Map.of(
            "username", authentication.getName(),
            "roles", authentication.getAuthorities()
        );
    }
}