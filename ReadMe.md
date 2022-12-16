# Traveling SalesMan Problem

## Funkcjonalność
- dodawanie miast
- dodawania połączeń miedzy miastami
- znajdowanie najkrótszej ścieżki
- zapis wyniku jako zdjęcie do galerii

## Wygląd

> ### Dodawanie miast
| Ξ             | Dodaj Miasto |
|---------------|--------------|
| Nazwa Miasta  | Text Input   |
| ------------- | ------------ |
| Submit Button |

> ### Dodawanie połączeń
| Ξ             | Dodaj Połączenie |
|---------------|------------------|
| Nazwa Miasta  | Dropdown         |
| ------------- | ---------------- |
| Nazwa Miasta  | Dropdown         |
| ------------- | ---------------- |
| Długość trasy | Number Input     |
| ------------- | ---------------- |
| Submit Button |

> ### Znajdź Trasę
| Ξ                        | Znajdź Trasę |
|--------------------------|--------------|
| Miasto Rozpoczęcia       | Dropdown     |
| ------------------       | ------------ |
| Require Singular Visit   | Check Box    |
| Wyszukaj Trasę           | Button       |
| ------------------       | ------------ |
| Show Duplicates          | Check Box    |
| ------------------       | ------------ |
| Total Traveling Distance | Text View    |
| Numbered List Of Nodes   |
| ------------------       |     
| Save Button              |

> ### Miasta | Połączenia
| Ξ                  | Title                        |
|--------------------|------------------------------|
| Lista Z Elementami | Auto Generated Delete Button |
| If Empty           | "No (Elements) Found "       |


## Rozwiązania
- AutoCompleteTextView
- Traveling SalesMan Algorithm
- Dialog View
- Nodes Not Represented As In Matrix (list Of Connections)
- Reflections
- Fragments For Display
- Buttons For Adding

## Projekt
> Layout bazowany na dynamicznym generowanie oraz layout_weight <br />
> Po uruchomieniu: wyświetla głównego fragmentu <br />
> Możliwość zmainy widocznego fragmentu za pomocą tab layout i gesture swipe <br />
> Zminimalizowanie aplikacji nie czyści danych <br />
> Zamknięcie aplikacji czyści dane <br />

## Funkcjonowanie
> Możliwość zmainy widocznego fragmentu za pomocą tab layout i gesture swipe <br />
> Czynności nie zajmujące dużej ilości czasu nie używają przycisków <br />
> Wybór danych na Spinner <br />
> Dodawanie danych na TextEdit <br />
> Dodatkowo losowe nazwy miast pobierane z publicznego api <br />
