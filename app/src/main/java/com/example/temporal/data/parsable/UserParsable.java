package com.example.temporal.data.parsable;

import com.example.temporal.domain.User;
import com.example.temporal.domain.specifics_domains.BasicUserDTO;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class UserParsable {

    public static Map<String, Object> parseUserForHash(FirebaseUser user) {
        Map<String, Object> map = new HashMap<>();

        map.put(User.UID, user.getUid());
        map.put(User.NAME, user.getDisplayName());
        map.put(User.PHONE, user.getPhoneNumber());
        map.put(User.EMAIL, user.getEmail());
        map.put(User.LASTNAME, "");

        return map;
    }

    public static User parseFromAuth(FirebaseUser user) {
        if (user == null) return null;

        User mUser = new User();
        mUser.setUid(user.getUid());
        mUser.setPhone(user.getPhoneNumber());
        mUser.setEmail(user.getEmail());
        mUser.setName(user.getDisplayName());
        mUser.setLastName("");

        return mUser;
    }

    public static User parseFromMap(Map<String, Object> map) {
        if (map == null) return null;
        User user = new User();

        user.setName(fillStringNotNull(map.get(User.NAME)));
        user.setPhone(fillStringNotNull(map.get(User.PHONE)));
        user.setEmail(fillStringNotNull(map.get(User.EMAIL)));
        user.setUid(fillStringNotNull(map.get(User.UID)));
        user.setLastName(fillStringNotNull(map.get(User.LASTNAME)));

        return user;
    }

    public static List<BasicUserDTO> parseUsers(List<DocumentSnapshot> list) {
        List<BasicUserDTO> users = new ArrayList<>();

        for (DocumentSnapshot documentSnapshot : list) {
            users.add(documentSnapshot.toObject(BasicUserDTO.class));
        }

        return users;
    }

    private static String fillStringNotNull(Object object) {
        return object == null ? "" : (String) object;
    }

}
