package hemu.ment.comm.message;

import java.io.Serializable;

/**
 * Created by muu on 2015/6/5.
 */
public class TransferMessage implements Serializable {

	private static final long serialVersionUID = 926502455456056219L;

	private long source;

	private long destination;

	private int type;

	private String message;

}
