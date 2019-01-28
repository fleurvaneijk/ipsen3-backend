package nl.hsleiden.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import java.security.Principal;
import java.util.Arrays;

import nl.hsleiden.View;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Meer informatie over validatie:
 *  http://hibernate.org/validator/
 * 
 * @author Peter van Vliet
 */
public class User implements Principal
{
    @NotEmpty
    @Email
    @JsonView(View.Public.class)
    private String email;

    @NotEmpty
    @Length(max = 100)
    @JsonView(View.Public.class)
    private String firstname;

    @NotEmpty
    @Length(max = 100)
    @JsonView(View.Public.class)
    private String lastname;
    
    @NotEmpty
    @Length(min = 3)
    @JsonView(View.Protected.class)
    private String password;
    
    @JsonView(View.Private.class)
    private String role;

    private String[] roleList = { "PARENT", "ADMIN", "MEDEWERKER" };

    public User(String email, String firstname, String lastname, String password, String role) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.role = role;
    }

    public User() { }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailAddress()
    {
        return email;
    }

    public void setEmailAddress(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    @JsonIgnore
    public String getName()
    {
        return firstname;
    }
    
    public String getRole()
    {
        return role;
    }

    public void setRole(String roles)
    {
        this.role = roles;
    }
    
    public boolean hasRole(String roleName)
    {
        if (roleList != null)
        {
            for(String role : roleList)
            {
                if(roleName.equals(role))
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean equals(User user)
    {
        return email.equals(user.getEmailAddress());
    }

    /**
     * This is only for testing purpose.
     * @return
     */
    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", roleList=" + Arrays.toString(roleList) +
                '}';
    }
}
