// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

package simsigGatewayInterface;

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

/**
 * <code>Harness</code> is an interface that collects
 * SimSig messages from the SimSig server. Project-specific implementations
 * must implement this interface in order to retrieve messages.
 *
 * @author Mark David Pokorny
 * @version Dé Céadaoin, 27ú Aibreán 2016
 * @since Dé Céadaoin, 27ú Aibreán 2016
 */
public interface Harness
{

// ------------------------------------------- Harness Interface ---------------

  /**
   * This method is called once the {@link SimSigClient} has connected to the
   * server and subscribed to the message feed. Returning from this method
   * starts the process of unsubscribing and disconnecting from the server.
   */
  void connected();

// ------------------------------------------- Harness Interface ---------------

  /**
   * This method is called once the {@link SimSigClient} has parsed a
   * {@link SimSigMessage} from the server.
   */
  void sendMessage(SimSigMessage message);

// ------------------------------------------- Harness Interface ---------------

} // End ‘Harness’ Interface

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+