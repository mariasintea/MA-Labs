import 'package:discover_destination/repository/destinations_repository.dart';
import 'package:discover_destination/repository/users_repository.dart';
import 'package:discover_destination/service/destinations_service.dart';
import 'package:discover_destination/service/users_service.dart';
import 'package:discover_destination/view/log_in_screen.dart';
import 'package:discover_destination/viewmodel/destinations_view_model.dart';
import 'package:discover_destination/viewmodel/users_view_model.dart';
import 'package:flutter/material.dart';

void main() {
  DestinationsService dService = DestinationsService();
  DestinationsRepository dRepository = DestinationsRepository(dService);
  DestinationsViewModel dViewModel = DestinationsViewModel(dRepository);
  UsersService uService = UsersService();
  UsersRepository uRepository = UsersRepository(uService);
  UsersViewModel uViewModel = UsersViewModel(uRepository);
  runApp(App(dViewModel, uViewModel));
}

class App extends StatelessWidget {
  UsersViewModel usersViewModel;
  DestinationsViewModel destinationsViewModel;

  App(this.destinationsViewModel, this.usersViewModel, {Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        debugShowCheckedModeBanner: false,
        home: LoginScreen(destinationsViewModel, usersViewModel),
    );
  }
}