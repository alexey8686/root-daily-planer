package com.bae.spb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;

@Document
@Getter
@Setter
@NoArgsConstructor
public class Authority extends AbstractBaseEntity implements GrantedAuthority {

    @Field
    private Role role;

    public Authority(Role role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role.name();
    }
}
