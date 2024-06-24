package lk.ijse.bo.custom.boImpl;

import lk.ijse.Entity.User;
import lk.ijse.Model.UserDTO;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.UserDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBoImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public ArrayList<UserDTO> getAllUser() throws SQLException, ClassNotFoundException {
        ArrayList<User> allUser = userDAO.getAll();
        ArrayList<UserDTO> allUsers = new ArrayList<>();

        for (User user : allUser) {
            allUsers.add(new UserDTO(user.getUId(),user.getUName(),user.getUEmail(),user.getUPassword(),user.getAddressLine1(),user.getAddressLine2(),user.getPhone(),user.getGender(),user.getDob(),user.getImageData()));
        }
        return allUsers;
    }

    @Override
    public boolean saveUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return userDAO.save(new User(userDTO.getUId(),userDTO.getUName(),userDTO.getUEmail(),userDTO.getUPassword(),userDTO.getAddressLine1(),userDTO.getAddressLine2(),userDTO.getPhone(),userDTO.getGender(),userDTO.getDob(),userDTO.getImageData()));
    }

    @Override
    public void updateUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        userDAO.update(new User(userDTO.getUId(),userDTO.getUName(),userDTO.getUEmail(),userDTO.getUPassword(),userDTO.getAddressLine1(),userDTO.getAddressLine2(),userDTO.getPhone(),userDTO.getGender(),userDTO.getDob(),userDTO.getImageData()));
    }

    @Override
    public void deleteUser(String id) throws SQLException, ClassNotFoundException {
        userDAO.delete(id);
    }

    @Override
    public boolean existUser(String id) throws SQLException, ClassNotFoundException {
        return userDAO.exist(id);
    }
}
