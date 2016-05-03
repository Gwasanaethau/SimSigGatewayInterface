// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

package simsigGatewayInterface;
import java.util.HashMap;

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

/**
 * Contains the details of a single SimSig message.
 *
 * @author Mark David Pokorny
 * @version Dé Máirt, 3ú Bealtaine 2016
 * @since Dé Céadaoin, 27ú Aibreán 2016
 */
public class SimSigMessage
{

// ----------------------------------------- SimSigMessage Class ---------------

  private MessageType type;
  private String simulation;
  private HashMap<String, String> parameters;

// ----------------------------------------- SimSigMessage Class ---------------

  SimSigMessage(
    MessageType type, String simulation, HashMap<String, String> parameters)
  {
    this.type = type;
    this.simulation = simulation;
    this.parameters = parameters;
  } // End ‘SimSigMessage(MessageType, String, HashMap<String, String>)’ Constructor

// ----------------------------------------- SimSigMessage Class ---------------

  public MessageType getType(){ return type; }
  public String getSimulation(){ return simulation; }
  public HashMap<String, String> getParameters(){ return parameters; }

// ----------------------------------------- SimSigMessage Class ---------------

} // End ‘SimSigMessage’ Class

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
