package be.ehb.lennert.application.frameworks.demo.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${STRIPE_SK}")
    private String API_SECRET_KEY;

    @Autowired
    public PaymentService() {
        Stripe.apiKey = API_SECRET_KEY;
    }

    public Charge charge(String token, double amount) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(amount * 100));
        chargeParams.put("currency", "USD");
        chargeParams.put("source", token);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }

    public PaymentIntent createIntent(double amount) throws StripeException {
        Stripe.apiKey = API_SECRET_KEY;
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount((long) amount *100)
                        .setCurrency("eur")
                        .addPaymentMethodType("bancontact")
                        .setPaymentMethodOptions(
                                PaymentIntentCreateParams.PaymentMethodOptions.builder()
                                        .putExtraParam("bancontact[preferred_language]", "nl")
                                        .build()
                        )
                        .build();


        return PaymentIntent.create(params);
    };

    public PaymentIntent getIntent(String client_secret) throws StripeException {
        Stripe.apiKey = API_SECRET_KEY;
        return PaymentIntent.retrieve(client_secret);
    }
}
