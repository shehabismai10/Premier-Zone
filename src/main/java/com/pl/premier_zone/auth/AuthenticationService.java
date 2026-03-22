package com.pl.premier_zone.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pl.premier_zone.config.JwtService;
import com.pl.premier_zone.user.Role;
import com.pl.premier_zone.user.User;
import com.pl.premier_zone.user.UserRepository;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    // الـ Constructor اليدوي لضمان الـ Dependency Injection حتى لو Lombok مهنج
    public AuthenticationService(
            UserRepository userRepository,
            JwtService jwtService,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    /**
     * ميثود التسجيل: بتحول الطلب لمستخدم جديد وتحفظه وتطلع له Token
     */
    public AuthenticationResponse register(RegisterRequest request) {
        // استخدام الـ Setters اليدوية بدل الـ Builder المعطل
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);

        // توليد التوكن
        var jwtToken = jwtService.generateToken(user);
        
        // بناء الـ Response يدوي
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(jwtToken);
        
        return response;
    }

    /**
     * ميثود تسجيل الدخول: بتتأكد من البيانات وبترجع التوكن
     */
    public AuthenticationResponse login(AuthenticationRequest request) {
        // التأكد من صحة الإيميل والباسورد عبر الـ AuthenticationManager
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );


        

        // لو البيانات صح، بنجيب المستخدم من الداتا بيز
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // توليد توكن جديد للجلسة الحالية
        var jwtToken = jwtService.generateToken(user);

        // إرجاع التوكن في الـ Response
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(jwtToken);
        
        return response;
    }
}