// Σταύρου Ιωάννης - icsd14190

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.registry.LocateRegistry;

public class MainServer 
{
    public static void main(String args[]) throws RemoteException
    {
        try
        {
            // Αρχικοποιώ το σύστημα ασφαλείας.
            SurveillanceSystem ss = new SurveillanceSystem();
            
            /* Ορίζω τη διεύθυνση στην οποία θα βρίσκεται το αντικείμενο που θα είναι
               διαθέσιμο προς αναζήτηση από τους πελάτες για την κλήση των επιθυμητών υπηρεσιών.*/
            LocateRegistry.createRegistry(1099);
            Naming.rebind("//localhost/Server", ss);
            System.out.println("Server up and running...\n");
            
            // Τρέχω ένα thread που αναπαριστά το σύστημα ανίχνευσης κίνησης του συστήματος.
            System.out.println("~ ~ ~ ~ ~ ~ ~ ~ Now detecting movement ~ ~ ~ ~ ~ ~ ~ ~");
            new MotionDetector().start();
        }
        catch (MalformedURLException ex) { }
    }
}