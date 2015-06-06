package hemu.ment.comm.message;

import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Created by muu on 2015/6/5.
 */
public class MessageEncoder implements Encoder.Text<TransferMessage> {

	@Override
	public String encode(TransferMessage transferMessage) throws EncodeException {
		return new Gson().toJson(transferMessage);
	}

	@Override
	public void init(EndpointConfig config) {

	}

	@Override
	public void destroy() {

	}
}