package cz.cvut.felk.via.data;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * Store one user comment.
 * @author Libor Bukata
 */
@PersistenceCapable
public class CommentInfo implements Serializable {
	private static final long serialVersionUID = 46783577647L;

	@Persistent
    private static String comment;

    @Persistent
    private static String userNick;
    
    @Persistent
    private Date timeStamp;

    
	public static String getComment() {
		return comment;
	}

	public static void setComment(String comment) {
		CommentInfo.comment = comment;
	}

	public static String getUserNick() {
		return userNick;
	}

	public static void setUserNick(String userNick) {
		CommentInfo.userNick = userNick;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}    
}
