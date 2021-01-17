package be.ehb.lennert.application.frameworks.demo.controllers;


import be.ehb.lennert.application.frameworks.demo.services.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

//https://stackabuse.com/stripe-integration-with-java-spring-for-payment-processing/
//Not fully functioning ( no charging )
@Controller
@RequestMapping("/payment")
@CrossOrigin("*")
public class PaymentController {


    @Value("${STRIPE_PK}")
    private String stripePublicKey;

    @Autowired
    PaymentService service;

    //@GetMapping(value = "/{amount}")
    @GetMapping()
    @ResponseBody
    public ResponseEntity<Object> home( Model model) {
        model.addAttribute("stripePublicKey", stripePublicKey);
        return new ResponseEntity<Object>(model, HttpStatus.OK);
    }

    //not used
    @GetMapping(value = "/charge")
    @ResponseBody
    public ResponseEntity<Object> charge(@RequestParam("payment_intent") String intentId,@RequestParam("payment_intent_client_secret") String client_secret, @RequestParam("redirect_status") String redirect_status, Model model) throws Exception {

       PaymentIntent intent =  service.getIntent(intentId);
        model.addAttribute("status",intent.getStatus());
        return new ResponseEntity<Object>(model,HttpStatus.OK);
    }
//http://localhost:3000/callbackPayment?payment_intent=pi_1I31mxB9G0CsXo9KQIsgphXx&payment_intent_client_secret=pi_1I31mxB9G0CsXo9KQIsgphXx_secret_uLiUSqVQVOtpvTXp61wqhNKPo&redirect_status=succeeded
    @GetMapping(value = "/clientSecret")
    @ResponseBody
    public String pay(HttpServletRequest request) throws Exception {
        Double amount = Double.parseDouble(request.getParameter("amount"));
        PaymentIntent intent = service.createIntent(amount);

        Map<String, String> map = new HashMap();
        map.put("client_secret", intent.getClientSecret());

        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(map);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

}
