@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Map<String, String>> users() {
        // Demo data
        return List.of(
            Map.of("id", "1", "username", "alice"),
            Map.of("id", "2", "username", "bob")
        );
    }
}