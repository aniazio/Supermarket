# Supermarket

**ENG**

**Description of the task:**

In a certain supermarket, there are M checkouts (M>3). The rules of their operation accepted by the store manager are as follows:
- There is always at least 2 open chceckouts.
- For every K customers in the store, there should be at least 1 open checkout.
- If the number of customers is less than K*(N-1), where N is the number of open checkouts, one of the checkouts shoud be closed.
If there were clients waiting in the checkout queue (before the decision to close was announced) then they should be served by that checkout. Clients come to the supermarket at random points in time and stay there for a certain random time for each of them. Write a program for clients and store manager.

The current status in the store is shown in the application. In addition, it is printed to the console so the changes, which have occurred throughout the day, can be tracked afterwards. Multithreading plays a significant role in this project.

**PL**

**Opis zadania:**

W pewnym supermarkecie jest łącznie M kas (M>3). Zasady ich działania przyjęte przez kierownika sklepu są następujące:
- Zawsze działają min. 2 stanowiska kasowe.
- Na każdych K klientów znajdujących się na terenie supermarketu powinno przypadać min. 1 czynne stanowisko kasowe.
- Jeśli liczba klientów jest mniejsza niż K*(N-1), gdzie N oznacza liczb czynnych kas, to jedna z kas zostaje zamknięta.
Jeśli w kolejce do kasy czekali klienci (przed ogłoszeniem decyzji o jej zamknięciu) to powinni zostać obsłużeni przez tę kasę. Klienci przychodzą do supermarketu w losowych momentach czasu i przebywają w nim przez pewien określony losowy dla każdego z nich czas. Napisz program klienta i kierownika kasjerów.

Aktualny stan w sklepie jest wyświetlany w aplikacji. Dodatkowo jest on wyświetlany na konsoli tak, aby można było prześledzić zmiany, jakie zaszły w ciągu całego dnia pracy supermarketu. W projekcie dużą rolę odgrywa programowanie wielowątkowe.
