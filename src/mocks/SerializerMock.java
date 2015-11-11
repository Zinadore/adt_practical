package mocks;

import java.io.File;
import java.io.IOException;

import Collections.ListInterface;
import Collections.ReferenceBasedList;
import model.ISerializer;
import model.User;

public class SerializerMock implements ISerializer<User> {
	
	private ListInterface<User> users;
	
	public SerializerMock() {
		users = new ReferenceBasedList<User>();
		users.append(new User("User1", "123456"));
		users.append(new User("User2", "123456"));
		users.append(new User("User3", "123456"));
		users.append(new User("User4", "123456"));
		
	}
	
	@Override
	public void serialize(ListInterface<User> serializable, File file) throws IOException {
		return;
	}

	@Override
	public ListInterface<User> deserialize( File file) throws IOException, ClassNotFoundException {
		return users;
	}

}
