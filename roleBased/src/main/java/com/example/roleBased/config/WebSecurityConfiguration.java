package com.example.roleBased.config;

import com.example.roleBased.serviceImpl.JwtService;
import io.jsonwebtoken.impl.crypto.JwtSigner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,proxyTargetClass = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private jwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    @Override
   public AuthenticationManager authenticationManagerBean() throws  Exception{
        return super.authenticationManagerBean();
    }

    //configure security via http methods
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/products/","/authenticate","/registerNewUser","/products/category/{catId}","/products/search","/products/recommend/{productId}","/coupon/","/products/recommendation").permitAll()
                .antMatchers("/category/save","/products/save/{subCatId}","/products/{id}","/category/**",
            "/category/{id}",
            "/subCat/save/{catId}",
            "/subCat/{id}",
            "/coupon/{subCatId}",
            "/coupon/{couponId}",
            "/subCat/","/").hasAuthority("Admin")
                .antMatchers("order/{userId}/{price}/{quantity}/{productId}",
                        "order/{userId}/{price}",
                        "/product/{productId}/order",
                        "/users/{userId}/order","/deleteCart/{userId}",
                        "/addtocart/{productId}/{userId}/{quantity}/{productExistInCart}",
                        "/deleteCart/{productId}/{userId}",
                        "/carts/getCartDetails",
                        "/{userId}",
                        "/userInfo/{userId}",
                "/{quantity}/{productId}/{userId}/{productExistInCart}",
                        "/{productId}",
                        "/{userId}/{productId}",
                        "/save/{userId}/product/{productId}",
                        "/getList/{userId}",
                        "/{cartId}/{productId}",
                        "/coupon/random/{userId}",
                        "/coupon/applyCoupon",
                        "/{userId}")
                .hasAuthority("User")
                .antMatchers(HttpHeaders.ALLOW).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //authentication manager builder class
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder)throws Exception{
        authenticationManagerBuilder.userDetailsService(jwtService).passwordEncoder(passwordEncoder());
    }
}
