import 'package:discover_destination/model/user.dart';
import 'package:discover_destination/service/users_service.dart';

class UsersRepository{
  UsersService service;

  UsersRepository(this.service);

  bool checkUser(User user) {
    return service.checkUser(user);
  }
}