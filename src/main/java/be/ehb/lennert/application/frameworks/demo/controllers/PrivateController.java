package be.ehb.lennert.application.frameworks.demo.controllers;

import net.minidev.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping(path = "private", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
@Component
public class PrivateController {

    @RequestMapping(value = "/users/", method = RequestMethod.GET)
    @ResponseBody
    public String privateEndpoint() {
        return "DONE";
    }
}
