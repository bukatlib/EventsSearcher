package cz.cvut.felk.via.data;

import java.io.Serializable;
import java.util.Date;

/**
 * Store one user comment.
 * @author Libor Bukata
 */

public class CommentInfo implements Serializable {
	
	private static final long serialVersionUID = 46783577647L;

    private String comment;
    private String userNick;
    private Date timeStamp;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}    
}
