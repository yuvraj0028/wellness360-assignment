package com.application.taskmanagement.db;

import com.application.taskmanagement.model.Task;
import com.application.taskmanagement.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an in-memory database for managing tasks.
 * <p>
 * This implementation uses a {@link HashMap} to store tasks, ensuring
 * efficient access and operations. By leveraging an in-memory storage approach,
 * this database provides the following benefits:
 * </p>
 * <ul>
 *     <li>Reduced complexity compared to file-based databases.</li>
 *     <li>Faster read/write operations without the overhead of file I/O.</li>
 *     <li>Simplified data handling, ideal for lightweight and temporary storage needs.</li>
 * </ul>
 */
public class DB {
    /**
     * A static {@link HashMap} to store tasks, keyed by their unique ID.
     */
    public static Map<String, Task> tasks = new HashMap<>();

    /**
     * A static {@link HashMap} to store users, keyed by their email address.
     */
    public static Map<String, User> users = new HashMap<>();
}
