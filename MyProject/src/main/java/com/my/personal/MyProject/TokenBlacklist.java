package com.my.personal.MyProject;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenBlacklist {
    // Thread-safe set of revoked tokens
    private final Set<String> blacklist = Collections.newSetFromMap(new ConcurrentHashMap<>());

    public void revoke(String token) {
        blacklist.add(token);
    }

    public boolean isRevoked(String token) {
        return blacklist.contains(token);
    }
}

