
package io.github.dmgtechlabs.users;

class SystemManager extends Users {

  public SystemManager(UserRepository userRepository, RoomManager roomManager, HotelManager hotelManager) {
    super(userRepository, roomManager, hotelManager);
  }
}
/*
 * package io.github.dmgtechlabs.users;
 * 
 * import io.github.dmgtechlabs.hotel.Hotel;
 * import io.github.dmgtechlabs.hotel.HotelManager;
 * import io.github.dmgtechlabs.hotel.Room;
 * import io.github.dmgtechlabs.hotel.RoomManager;
 * import io.github.dmgtechlabs.users.dto.UserDto;
 * import io.github.dmgtechlabs.users.dto.UserDto.UserStatus;
 * import io.github.dmgtechlabs.users.entity.User;
 * import io.github.dmgtechlabs.users.repository.UserRepository;
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.stereotype.Service;
 * 
 * import java.util.List;
 * 
 * @Service
 * public class SystemManager {
 * 
 * @Autowired
 * private UserRepository userRepository;
 * 
 * @Autowired
 * private RoomManager roomManager;
 * 
 * @Autowired
 * private HotelManager hotelManager;
 * 
 * public UserDto createUser(UserDto userDto) {
 * User user = new User();
 * user.setUsername(userDto.getUsername());
 * user.setPassword(userDto.getPassword());
 * user.setStatus(UserStatus.ACTIVE);
 * user.setRole(userDto.getRole());
 * userRepository.save(user);
 * return new UserDto(user);
 * }
 * 
 * public UserDto getUser(String username) {
 * User user = userRepository.findByUsername(username);
 * return new UserDto(user);
 * }
 * 
 * public UserDto getUserById(Long id) {
 * User user = userRepository.findById(id).get();
 * return new UserDto(user);
 * }
 * 
 * public UserDto updateUser(UserDto userDto) {
 * User user = userRepository.findById(userDto.getId()).get();
 * user.setUsername(userDto.getUsername());
 * user.setPassword(userDto.getPassword());
 * user.setStatus(userDto.getStatus());
 * user.setRole(userDto.getRole());
 * userRepository.save(user);
 * return new UserDto(user);
 * }
 * 
 * public UserDto deleteUser(Long id) {
 * User user = userRepository.findById(id).get();
 * userRepository.delete(user);
 * return new UserDto(user);
 * }
 * 
 * public List<UserDto> getAllUsers() {
 * List<User> users = userRepository.findAll();
 * return users.stream().map(UserDto::new).collect(List.of());
 * }
 * 
 * public List<UserDto> getAllActiveUsers() {
 * List<User> users = userRepository.findAllByStatus(UserStatus.ACTIVE);
 * return users.stream().map(UserDto::new).collect(List.of());
 * }
 * 
 * public List<UserDto> getAllInactiveUsers() {
 * List<User> users = userRepository.findAllByStatus(UserStatus.INACTIVE);
 * return users.stream().map(UserDto::new).collect(List.of());
 * }
 * 
 * public List<UserDto> getAllAdminUsers() {
 * List<User> users = userRepository.findAllByRole(User.Role.ADMIN);
 * return users.stream().map(UserDto::new).collect(List.of());
 * }
 * 
 * public List<UserDto> getAllManagerUsers() {
 * List<User> users = userRepository.findAllByRole(User.Role.MANAGER);
 * return users.stream().map(UserDto::new).collect(List.of());
 * }
 * 
 * public List<UserDto> getAllEmployeeUsers() {
 * List<User> users = userRepository.findAllByRole(User.Role.EMPLOYEE);
 * return users.stream().map(UserDto::new).collect(List.of());
 * }
 * 
 * public List<UserDto> getAllGuestUsers() {
 * List<User> users = userRepository.findAllByRole(User.Role.GUEST);
 * return users.stream().map(UserDto::new).collect(List.of());
 * }
 * 
 * public List<UserDto> getAllUsersByRole(User.Role role) {
 * List<User> users = userRepository.findAllByRole(role);
 * return users.stream().map(UserDto::new).collect(List.of());
 * }
 * 
 * public List<UserDto> getAllUsersByStatus(UserStatus status) {
 * List<User> users = userRepository.findAllByStatus(status);
 * return users.stream().map(UserDto::new).collect(List.of());
 * }
 * 
 * public List<UserDto> getAllUsersByHotel(Hotel hotel) {
 * List<User> users = userRepository.findAllByHotel(hotel);
 * return users.stream().map(UserDto::new).collect(List.of());
 * }
 * 
 * public List<UserDto> getAllUsersByRoom(Room room) {
 * List<User> users = userRepository.findAllByRoom(room);
 * return users.stream().map(UserDto::new).collect(List.of());
 * }
 * 
 * public List<UserDto> getAllUsersByHotelAndRoom(Hotel hotel, Room room) {
 * List<User> users = userRepository.findAllByHotelAndRoom(hotel, room);
 * return users.stream().map(UserDto::new).collect(List.of());
 * }
 * 
 * public List<UserDto> getAllUsersByRoomAndHotel(Room room, Hotel hotel) {
 * List<User> users = userRepository.findAllByRoomAndHotel(room, hotel);
 * return users.stream().map(UserDto::new).collect(List.of());
 * }
 * 
 * public List<UserDto> getAllUsersByHotelAndRoomAndStatus(Hotel hotel, Room
 * room, UserStatus status) {
 * List<User> users = userRepository.findAllByHotelAndRoomAndStatus(hotel, room,
 * status);
 * return users.stream().map(UserDto::new).collect(List.of());
 * }
 * 
 * public List<UserDto> getAllUsersByRoomAndHotelAndStatus(Room room, Hotel
 * hotel, UserStatus status) {
 * List<User> users = userRepository.findAllByRoomAndHotelAndStatus(room, hotel,
 * status);
 * return users.stream().map(UserDto::new).collect(List.of());
 * }
 * 
 * public List<UserDto> getAllUsersByHotelAndRoomAndStatusAndRole(Hotel hotel,
 * Room room, UserStatus status,
 * User.Role role) {
 * List<User> users =
 * userRepository.findAllByHotelAndRoomAndStatusAndRole(hotel, room, status,
 * role);
 * return users.stream().map(UserDto::new).collect(List.of());
 * }
 * 
 * public List<UserDto> getAllUsersByRoomAndHotelAndStatusAndRole(Room room,
 * Hotel hotel, UserStatus status,
 * User.Role role) {
 * List<User> users = userRepository.findAllByRoomAndHotelAndStatusAndRole(room,
 * hotel, status, role);
 * return users.stream().map(UserDto::new).collect(List.of());
 * }
 * 
 * }
 */
