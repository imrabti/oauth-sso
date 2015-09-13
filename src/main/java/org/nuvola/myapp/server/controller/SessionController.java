package org.nuvola.myapp.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.nuvola.myapp.shared.ApiPaths.SESSION;

@RestController
@RequestMapping(value = SESSION)
public class SessionController {
}
