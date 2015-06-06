package hemu.ment.comm.message;

import com.google.gson.Gson;

import javax.websocket.*;

/**
 * Created by muu on 2015/6/5.
 */
public class MessageDecoder implements Decoder.Text<TransferMessage> {
	@Override
	public TransferMessage decode(String string) throws DecodeException {
		return new Gson().fromJson(string, TransferMessage.class);
	}

	@Override
	public boolean willDecode(String s) {
		return true;
	}

	@Override
	public void init(EndpointConfig config) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void destroy() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}