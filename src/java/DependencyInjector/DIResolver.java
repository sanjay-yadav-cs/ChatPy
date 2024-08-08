/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DependencyInjector;

import DAL.*;
import Models.*;
import Repository.*;
import java.time.LocalDateTime;
import java.util.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DIResolver {

    private static Map<Class<?>, Class<?>> interfaceToImplMap = new HashMap<>();
    private static Map<Class<?>, Object> singletonInstances = new HashMap<>();

    static {
        DIResolver.register(ChatRoomService.class, ChatRoomServiceImplementation.class);
        DIResolver.register(MessageService.class, MessageServiceImplementation.class);
        DIResolver.register(UserService.class, UserServiceImplementation.class);

        DIResolver.registerSingleton(ChatRoomService.class, ChatRoomServiceImplementation.class);
        DIResolver.registerSingleton(MessageService.class, MessageServiceImplementation.class);
        DIResolver.registerSingleton(UserService.class, UserServiceImplementation.class);

        
        UserService userService = DIResolver.resolve(UserService.class);
        MessageService messageService = DIResolver.resolve(MessageService.class);
        ChatRoomService chatRoomService = DIResolver.resolve(ChatRoomService.class);
        
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            User user = new User(i, "User" + i, "password" + i, "profileImage" + i + ".png", "About User" + i);
            users.add(user);
            userService.createUser(user);
        }

        List<ChatRoom> chatRooms = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i <= 15; i++) {
            List<User> members = Arrays.asList(
                    users.get(random.nextInt(users.size())),
                    users.get(random.nextInt(users.size()))
            );
            ChatRoom chatRoom = new ChatRoom(i, "ChatRoom" + i, members, random.nextBoolean(), "roomImage" + i + ".png");
            chatRooms.add(chatRoom);
            chatRoomService.createChatRoom(chatRoom);
        }

        List<Message> messages = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            int roomId = random.nextInt(chatRooms.size()) + 1;
            int senderId = random.nextInt(users.size()) + 1;
            int receiverId = random.nextInt(users.size()) + 1;
            Message message = new Message(i, roomId, senderId, receiverId, LocalDateTime.now().minusDays(random.nextInt(30)), "Message content " + i);
            messages.add(message);
            messageService.createMessage(message);
        }
        
    }

    public static <T> void register(Class<T> interfaceClass, Class<? extends T> implementationClass) {
        interfaceToImplMap.put(interfaceClass, implementationClass);
    }

    public static <T> T resolve(Class<T> interfaceClass) {
        if (singletonInstances.containsKey(interfaceClass)) {
            return interfaceClass.cast(singletonInstances.get(interfaceClass));
        }

        Class<?> implementationClass = interfaceToImplMap.get(interfaceClass);
        if (implementationClass == null) {
            throw new RuntimeException("No implementation found for " + interfaceClass.getName());
        }
        try {
            return interfaceClass.cast(implementationClass.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            throw new RuntimeException("Failed to create instance of " + implementationClass.getName(), e);
        }
    }

    public static <T> void registerSingleton(Class<T> interfaceClass, Class<? extends T> implementationClass) {
        interfaceToImplMap.put(interfaceClass, implementationClass);
        try {
            T instance = implementationClass.getDeclaredConstructor().newInstance();
            singletonInstances.put(interfaceClass, instance);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create singleton instance of " + implementationClass.getName(), e);
        }
    }

}
