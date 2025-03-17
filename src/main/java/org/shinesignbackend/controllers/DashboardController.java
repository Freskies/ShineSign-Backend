package org.shinesignbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.shinesignbackend.services.DocumentService;
import org.shinesignbackend.services.ShineSignUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/dashboard")
@RequiredArgsConstructor
@Validated
@PreAuthorize ("hasRole('ROLE_USER')")
public class DashboardController {
	private final DocumentService documentService;
	private final ShineSignUserService shineSignUserService;


}
