package teamminus1.udirtyrat.models;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * A temp serialization handler used for persistence within the app
 */

class TempSerializationHandler {

    private final Context context;

    /**
     * Constructor
     * @param context context of current app
     */
    public TempSerializationHandler(Context context) {
        this.context = context;
    }

    /**
     * Writes all the rats to file
     */
    void WriteRatsToFile() {
        try {
            FileOutputStream fileOut = context.openFileOutput(Model.RAT_FILE_NAME,
                    Context.MODE_PRIVATE);
                    //new FileOutputStream(Model.RAT_FILE_NAME);
            ObjectOutput out = new ObjectOutputStream(fileOut);
            out.writeObject(RatRegistry.Instance().allRatSightings());
            out.close();
            fileOut.close();
        } catch (IOException i) {
            Log.d("Write", Arrays.toString(i.getStackTrace()));
        }
    }

    /**
     * Writes all the users to file
     */
    void WriteUsersToFile() {
        try {
            FileOutputStream fileOut = context.openFileOutput(Model.USER_FILE_NAME,
                    Context.MODE_PRIVATE);
            //new FileOutputStream(Model.RAT_FILE_NAME);
            ObjectOutput out = new ObjectOutputStream(fileOut);
            out.writeObject(UserRegistry.Instance().AllUsers());
            out.close();
            fileOut.close();
        } catch (IOException i) {
            Log.d("Write", Arrays.toString(i.getStackTrace()));
        }
    }

    /**
     * Read rats from file
     */
    void ReadRatsFromFile() {
        try {
            List<RatSighting> sightings;
            FileInputStream fileIn = context.openFileInput(Model.RAT_FILE_NAME);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            sightings = (List<RatSighting>) in.readObject();
            in.close();
            fileIn.close();
            RatRegistry.Instance().addRatsFromFile(sightings);
            Model.setLoadedFromFileToTrue();
        } catch (IOException | ClassNotFoundException e) {
            Log.d("Read", e.getMessage());
        }
    }

    /**
     * Read users from file
     */
    void ReadUsersFromFile() {
        List<IUser> users;
        try {
            //List<RatSighting> sightings;
            FileInputStream fileIn = context.openFileInput(Model.USER_FILE_NAME);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            users = (List<IUser>) in.readObject();
            in.close();
            fileIn.close();
            UserRegistry.Instance().addUsersFromFile(users);
        } catch (IOException | ClassNotFoundException e) {
            Log.d("Read", e.getMessage());
        }
    }
}
