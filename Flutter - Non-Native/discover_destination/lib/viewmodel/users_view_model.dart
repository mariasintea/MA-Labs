import 'package:discover_destination/model/user.dart';
import 'package:discover_destination/repository/users_repository.dart';

class UsersViewModel{
  UsersRepository repository;

  UsersViewModel(this.repository);

  bool checkUser(User user) {
    return repository.checkUser(user);
  }
}