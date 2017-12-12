package teamminus1.udirtyrat.models;

import java.io.Serializable;

/**
 * Interface to manage different types of users
 */

public interface IUser extends Serializable{
    String getUsername();
    String getPassword();
    String getToken();
    String setToken( String newtoken);
}
