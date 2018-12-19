package com.example.demo.resources.users;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;



@RestController
public class UserRestController {
	
//	@Autowired
//	private UserDataService userDataservice;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;

	@RequestMapping(value="/users", method=RequestMethod.GET)
	public List<User> findAll() {
		//return userDataservice.findAll();
		return userRepository.findAll();
	}
	
	@RequestMapping(value="/users/{id}", method=RequestMethod.GET)
	public Resource<User> findOne(@PathVariable int id) {
		//User user = userDataservice.findOne(id);
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("id-"+id);
		//HATEOAS
		//"all-users, SERVER_PATH + "/users"
		//findAll
		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findAll());
		resource.add(linkTo.withRel("all-users"));
		//
		return resource;
	}
	
	@RequestMapping(value="/users", method=RequestMethod.POST)
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		//User savedUser = userDataservice.save(user);
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@RequestMapping(value="/users/{id}", method=RequestMethod.DELETE)
	public User requestMethodName(@PathVariable int id) {
		//User user = userDataservice.deleteOne(id);
		Optional<User> user = userRepository.findById(id);
		userRepository.deleteById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("id-"+id);
		return user.get();
	}
	
	@RequestMapping(value="/users/{id}/posts", method=RequestMethod.GET)
	public List<Post> retrievePosts(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent())
			throw new UserNotFoundException("id = "+id);
		
		return user.get().getPosts();
		
	}
	
	@RequestMapping(value="/users/{id}/posts", method=RequestMethod.POST)
	public ResponseEntity<Object> createPost(@PathVariable int id,@RequestBody Post post) {
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent())
			throw new UserNotFoundException("id = "+id);
		User user = userOptional.get();
		
		post.setUser(user);
		
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	
}
