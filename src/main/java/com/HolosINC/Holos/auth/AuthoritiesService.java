package com.HolosINC.Holos.auth;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.HolosINC.Holos.artist.Artist;
import com.HolosINC.Holos.artist.ArtistService;
import com.HolosINC.Holos.auth.payload.request.SignupRequest;
import com.HolosINC.Holos.client.Client;
import com.HolosINC.Holos.client.ClientService;
import com.HolosINC.Holos.exceptions.ResourceNotFoundException;
import com.HolosINC.Holos.model.BaseUser;
import com.HolosINC.Holos.model.BaseUserService;

@Service
public class AuthoritiesService {

	private final PasswordEncoder encoder;
	private final BaseUserService baseUserService;
	private final ArtistService artistService;
	private final ClientService clientService;
	private final AuthoritiesRepository authoritiesRepository;

	@Autowired
	public AuthoritiesService(PasswordEncoder encoder, BaseUserService baseUserService, ArtistService artistService, ClientService clientService, AuthoritiesRepository authoritiesRepository) {
		this.encoder = encoder;
		this.baseUserService = baseUserService;
		this.artistService = artistService;
		this.clientService = clientService;
		this.authoritiesRepository = authoritiesRepository;
	}

	@Transactional
	public Authorities findByAuthority(String authority) {
		Optional<Authorities> authorities = authoritiesRepository.findByName(authority);
		if (!authorities.isPresent()) {
			throw new ResourceNotFoundException("Este rol no existe");
		}
		return authorities.get();
	}

	@Transactional
	public void createUser(@Valid SignupRequest request) {
		BaseUser user = new BaseUser();
		user.setUsername(request.getUsername());
		user.setName(request.getFirstName());
		user.setCreatedUser(Date.from(Instant.now()));
		user.setPassword(encoder.encode(request.getPassword()));
		String strRoles = request.getAuthority().toUpperCase();
		Authorities role = findByAuthority(strRoles);
		user.setAuthority(role);

		switch (strRoles.toLowerCase()) {
		case "admin":;
			baseUserService.save(user);
			break;
		case "client":
			Client client = new Client();
			baseUserService.save(user);
			client.setBaseUser(user);
			clientService.saveClient(client);
			break;
		case "artist":
			Artist artist = new Artist();
			baseUserService.save(user);
			artist.setBaseUser(user);
			artist.setName(request.getFirstName());
			artist.setUsername(request.getUsername());
			artistService.saveArtist(artist);
			break;
		default:
			baseUserService.save(user);
		}
	}

}
