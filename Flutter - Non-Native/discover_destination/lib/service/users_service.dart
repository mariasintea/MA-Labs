import 'package:discover_destination/model/user.dart';

class UsersService {
  List<User> users = List.of([User("ana", "pass"), User("ion", "pass")]);

  bool checkUser(User user) {
    return !users.where((u) => user.username == u.username && user.password == u.password).isEmpty;
  }
}