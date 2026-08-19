package com.crimsonlogic.ecommerce.interceptor;

import com.crimsonlogic.ecommerce.enumeration.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class RoleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler)
            throws Exception {

        HttpSession session =
                request.getSession(false);

        String requestUri =
                request.getRequestURI();

        String contextPath =
                request.getContextPath();

        // ==========================================================
        // NOT LOGGED IN
        // ==========================================================

        if (session == null ||
                session.getAttribute("loggedInUser") == null) {

            response.sendRedirect(
                    contextPath + "/"
            );

            return false;
        }


        // ==========================================================
        // GET ROLE
        // ==========================================================

        Object roleObject =
                session.getAttribute("role");

        if (roleObject == null) {

            session.invalidate();

            response.sendRedirect(
                    contextPath + "/"
            );

            return false;
        }


        Role role;

        try {

            if (roleObject instanceof Role) {

                role = (Role) roleObject;

            } else {

                role =
                        Role.valueOf(
                                roleObject.toString()
                        );
            }

        } catch (IllegalArgumentException exception) {

            session.invalidate();

            response.sendRedirect(
                    contextPath + "/"
            );

            return false;
        }


        // ==========================================================
        // ADMIN URLs
        // ==========================================================

        if (requestUri.startsWith(
                contextPath + "/admin")) {

            if (role == Role.ADMIN) {

                return true;
            }

            response.sendError(
                    HttpServletResponse.SC_FORBIDDEN,
                    "Access denied. Admin role required."
            );

            return false;
        }


        // ==========================================================
        // SELLER URLs
        // ==========================================================

        if (requestUri.startsWith(
                contextPath + "/seller")) {

            if (role == Role.SELLER) {

                return true;
            }

            response.sendError(
                    HttpServletResponse.SC_FORBIDDEN,
                    "Access denied. Seller role required."
            );

            return false;
        }


        // ==========================================================
        // CUSTOMER URLs
        // ==========================================================

        if (requestUri.startsWith(
                contextPath + "/customer")) {

            if (role == Role.CUSTOMER) {

                return true;
            }

            response.sendError(
                    HttpServletResponse.SC_FORBIDDEN,
                    "Access denied. Customer role required."
            );

            return false;
        }


     // ==========================================================
     // REPORTS
     // ==========================================================

     if (requestUri.startsWith(
             contextPath + "/reports/admin")) {

         if (role == Role.ADMIN) {

             return true;
         }

         response.sendError(
                 HttpServletResponse.SC_FORBIDDEN,
                 "Access denied. Admin role required."
         );

         return false;
     }


     // ==========================================================
     // SELLER REPORTS
     // ==========================================================

     if (requestUri.startsWith(
             contextPath + "/reports/seller")) {

         if (role == Role.SELLER) {

             return true;
         }

         response.sendError(
                 HttpServletResponse.SC_FORBIDDEN,
                 "Access denied. Seller role required."
         );

         return false;
     }


     // ==========================================================
     // CUSTOMER REPORTS
     // ==========================================================

     if (requestUri.startsWith(
             contextPath + "/reports/customer")) {

         if (role == Role.CUSTOMER) {

             return true;
         }

         response.sendError(
                 HttpServletResponse.SC_FORBIDDEN,
                 "Access denied. Customer role required."
         );

         return false;
     }


        // ==========================================================
        // INVENTORY
        // ==========================================================

        if (requestUri.startsWith(
                contextPath + "/inventory")) {

            if (role == Role.ADMIN ||
                    role == Role.SELLER) {

                return true;
            }

            response.sendError(
                    HttpServletResponse.SC_FORBIDDEN,
                    "Access denied."
            );

            return false;
        }


        // ==========================================================
        // PRODUCT
        // ==========================================================

        if (requestUri.startsWith(
                contextPath + "/product")) {

            if (role == Role.ADMIN ||
                    role == Role.SELLER ||
                    role == Role.CUSTOMER) {

                return true;
            }

            response.sendError(
                    HttpServletResponse.SC_FORBIDDEN,
                    "Access denied."
            );

            return false;
        }


        // ==========================================================
        // ADDRESS
        // ==========================================================

        if (requestUri.startsWith(
                contextPath + "/address")) {

            if (role == Role.ADMIN ||
                    role == Role.SELLER ||
                    role == Role.CUSTOMER) {

                return true;
            }

            response.sendError(
                    HttpServletResponse.SC_FORBIDDEN,
                    "Access denied."
            );

            return false;
        }


        // ==========================================================
        // DEFAULT
        // ==========================================================

        return true;
    }
}