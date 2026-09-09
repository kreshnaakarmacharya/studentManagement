package com.std.student.config;

import com.std.student.services.Security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/"
                        ).permitAll()

                        .requestMatchers("/admin/**",
                                "/students/add",
                                "/students/save",
                                "/students/studentList",
                                "/students/std/edit/{id}",
                                "/students/students/delete/{id}",
                                "/students/updateStudent",
                                "/students/view/{id}",
                                "/teacher/addTeacherForm",
                                "teacher/addTeacher",
                                "/teacher/teacherList",
                                "/teacher/editTeacher/{id}",
                                "/teacher/deleteTeacher/{id}",
                                "/teacher/updateTeacher",
                                "/teacher/view/{id}",
                                "/courses/addCourseForm",
                                "/courses/addCourse",
                                "/courses/courseList",
                                "/courses/view/{id}",
                                "/courses/editCourse/{id}",
                                "/courses/deleteCourse/{id}",
                                "/courses/updateCourse",
                                "/subjects/addSubjectForm",
                                "/subjects/addSubject",
                                "/subjects/subjectList",
                                "subjects/editSubject/{id}",
                                "/subjects/deleteSubject/{id}",
                                "/subjects/updateSubject",
                                "/teacher/*/subjects",
                                "/teacher/*/subjects/add",
                                "/teacher/*/subjects/delete/{subjectId}")
                        .hasRole("ADMIN")

                        .requestMatchers("/teacher/dashboard",
                                "/teacher/profile",
                                "/teacher//profile/edit",
                                "/teacher//profile/update")
                        .hasRole("TEACHER")

                        .requestMatchers("/students/dashboard",
                                "/students/profile",
                                "/students/profile/edit",
                                "/students/profile/update")
                        .hasRole("STUDENT")

                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler((request, response, authentication) -> {

                            var authorities = authentication.getAuthorities();

                            if (authorities.stream()
                                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {

                                response.sendRedirect("/admin/dashboard");

                            } else if (authorities.stream()
                                    .anyMatch(a -> a.getAuthority().equals("ROLE_TEACHER"))) {

                                response.sendRedirect("/teacher/dashboard");

                            } else if (authorities.stream()
                                    .anyMatch(a -> a.getAuthority().equals("ROLE_STUDENT"))) {

                                response.sendRedirect("/students/dashboard");

                            } else {
                                response.sendRedirect("/");
                            }
                        })
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );
        return http.build();
    }

}
