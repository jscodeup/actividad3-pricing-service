package co.edu.upb.pricing;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/pricing")
public class PricingController {

    @GetMapping("/estimate")
    public Map<String, Object> estimate(@RequestParam(defaultValue = "bogota") String city) {
        double base = 12000;
        double surge = city.equalsIgnoreCase("bogota") ? 1.4 : 1.0;
        double total = base * surge;

        return Map.of(
                "city", city,
                "currency", "COP",
                "baseFare", base,
                "surgeFactor", surge,
                "estimatedTotal", total
        );
    }
}
