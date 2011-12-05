package cz.cvut.felk.via.datalayer;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Text;

import cz.cvut.felk.via.data.CommentInfo;

/**
 * Store one user comment.
 * @author Libor Bukata
 */
@PersistenceCapable
public class CommentInfoJDO implements Serializable {
	
	private static final long serialVersionUID = 46783577647L;

    @Persistent
    private String userNick;
    
    @Persistent
    private Date timeStamp;

	@Persistent
    private Text comment;  
	
	public CommentInfoJDO() { }
	
	public CommentInfoJDO(CommentInfo ci)	{
		userNick = ci.getUserNick();
		timeStamp = ci.getTimeStamp();
		comment = new Text(ci.getComment());
	}
    
	public Text getComment() {
		return comment;
	}

	public void setComment(Text comment) {
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

