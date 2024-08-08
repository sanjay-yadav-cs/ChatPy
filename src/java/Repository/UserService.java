/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;
import Models.*;

import java.util.List;

public interface UserService {
    // Create a new user
    void createUser(User user);

    // Find a user by their ID
    User findUserById(int id);

    // Update an existing user
    void updateUser(User user);

    // Delete a user by their ID
    void deleteUser(int id);

    // Get all users
    List<User> findAllUsers();

    // Find a user by their username
    User findUserByUsername(String username);
}

