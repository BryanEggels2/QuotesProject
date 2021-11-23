package com.kabisa.quotes.services;

import com.kabisa.quotes.repositories.Repository;

/**
 * Enum for the services. This can be used to create more sources. For example if you wish to add a database service:
 * You should add "database" in this enum. This enum is used in {@link Repository#initialize(Service)}
 * to simply use the same service all over the application.
 */
public enum Service {
    Api,
    Mock,
}
