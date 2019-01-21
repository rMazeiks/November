# November
November is a JavaFX framework that takes care of letting the user arrange windows to customize the structure of the user interface.

## Inspiration
Adobe Photoshop (and other Adobe software) has a nice way of letting the user open the panels they need, while keeping other panels hidden. Blender lets the user open different sections of the UI in different places. JetBrains IDEs, like Intellij also let the user move tools to different locations on the screen.

In any of these applications, the user has control over how they use the application. They can close the clutter they don't need, make tools they use frequently larger, and organize the panels in a way that makes sense to them.

November was created to make it easy for applications to provide this kind of flexible user interface. Developers shouldn't worry about how to arrange tools on the screen. With November, all they have to do is design the individual tools (panels) and users will have them available.

## How it works
The application simply has to register Workspaces, each of which is responsible for:
- Providing its name (to be displayed to the user for selecting this workspace)
- Providing a way to generate a Node that represents this workspace's UI.

November then lets the user open any of these workspaces, on zero or more panels, and arrange them next to each other, on top of each other, or any nested combination of these.

The user may also group several workspaces in the same panel, making them appear as tabs that can be used one at a time.

## Sources
[Material Design](https://material.io/tools/icons/?style=baseline) icons used in parts of the user interface.