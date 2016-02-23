package com.theironyard;

import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    static HashMap<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        Spark.init();
        User kevin = new User("Kevin", "asdf");
        users.put(kevin.name, kevin);

        Spark.get(
                "/",
                ((request, response) -> {
                    User user = getUserFromSession(request.session());

                    HashMap m = new HashMap();
                    if (user == null) {
                        return new ModelAndView(m, "login.html");
                    } else {
                        return new ModelAndView(user, "messages.html");
                    }
                }),
                new MustacheTemplateEngine()
        );

        Spark.post(
                "/create-user",
                ((request, response) -> {
                    Session session = request.session();
                    String name = request.queryParams("loginName");
                    String password = request.queryParams("loginPassword");
                    //User user = users.get(name);
                    if (!users.containsKey(name)) {
                       User user = new User(name, password);
                        users.put(name, user);
                        session.attribute("userName", name);
                    } else {
                        User user = users.get(name);
                        if (user.password.equals(password)) {
                            session.attribute("userName", name);
                        }
                    }

                    response.redirect("/");
                    return "";
                })
        );

        Spark.post(
                "/create-message",
                ((request, response) -> {
                    User user = getUserFromSession(request.session());
                    if (user == null) {
                        Spark.halt(403);
                    }

                    String text = request.queryParams("message");
                    Messages messages = new Messages(text);

                    user.allMessages.add(messages);

                    response.redirect("/");

                    return "";
                })
        );

        Spark.post(
                "/delete-message",
                ((request, response) -> {
                    int index = Integer.valueOf(request.queryParams("selectMessage"));
                    Session session = request.session();
                    String name = session.attribute("userName");
                    User user = users.get(name);
                    user.allMessages.remove(index - 1);
                    response.redirect("/");
                    return "";
                })
        );

        Spark.post(
                "/edit-message",
                ((request, response) -> {
                    int index = Integer.valueOf(request.queryParams("selectMessage"));
                    Session session = request.session();
                    String name = session.attribute("userName");
                    User user = users.get(name);

                    String newMessage = request.queryParams("editMessage");

                    user.allMessages.get(index -1).text = newMessage;

                    response.redirect("/");
                    return "";
                })
        );

        Spark.post(
                "/logout",
                ((request, response) -> {
                    Session session = request.session();
                    session.invalidate(); //wipes out current session
                    response.redirect("/");
                    return "";
                })
        );








        //class name, object name = constructor method;




    }//end main method

    static User getUserFromSession(Session session) {
        String name = session.attribute("userName");
        return users.get(name);
    }
}