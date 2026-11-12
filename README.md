# Structure

Тут будет расписано только то, с чем взаимодействуем (остальное не трогать).
Тут нет вещей которые уже есть в документации LibGDX.

## Создание проекта
1) Копируем данную структуру с репозитория командой:
```
git clone https://github.com/Aleksandr12234/Structure
```

2) В файле build.gradle меняем следующие две строки:
```groovy
//Меняем Structure на нужное имя
ext.appName = 'Structure'

eclipse.project.name = 'Structure' + '-parent'
```

3) Изменить репозиторий структуры на собственный
```
git remote set-url origin <URL_нового_репозитория>
```

4) Проект лучше делать в директории "Project"

## Начальные настройки

### Lwjgl3Launcher
```java
//Все настройки приложения тут
private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
    //...
    
    // Смена иконок в lwjgl3/src/main/resources/ .
    configuration.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");
    
    //...
}
```

### BasicSettings

```java
public class BasicSettings {
    public static final String PROJECT_NAME="Structure"; //Название
    public static final boolean IS_FULLSCREEN=false; //Полный ли экран
    public static final int[] WINDOW_SIZE=new int[]{800, 500}; //Размер окна (если не включен полный экран)
    public static final int[] VIEWPORT=new int[]{8, 5}; //Размер Viewport
    public static final int FPS=60; //fps

    //начальная сцена
    public static EmptyScene getStartScene(){
        return new DefaultDevelopScene();
    }
}
```

## Примечания

! Если нужно указать путь к чему либо, считайте что он начинается в assets, то-есть:
```
"test/test.png" - Правильно

"C:/program/project/assets/test/test.png" - НЕПРАВИЛЬНО!!!!
```

! В структуре есть библиотека lombook, но желательно использовать её по минимуму. Хоть она и призвана 
для удобства и в целях сократить количество java кода (авто написание геттеров, сеттеров, класса строителя),
её авторы не добавили аннотаций исключений для builder-а 
и в каком-то смысле [послали пользователей](https://github.com/projectlombok/lombok/issues/2307).

## Остальное

- [Основные инструменты](doc/mainInstruments.md)
- [Debug](doc/Debug.md)
- [Пост обработка](doc/PostProcessing.md)
- [Объекты](doc/BasicObject.md)
- [Сцены](doc/Scene.md)
- [Коллизии](doc/Collision.md)
