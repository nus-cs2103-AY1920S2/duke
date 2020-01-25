[33mcommit 9386d22b0636ad93863f14ddb9564e6e269e5ad9[m[33m ([m[1;36mHEAD -> [m[1;32mmaster[m[33m)[m
Author: Clouddoggo <jel.lim99@gmail.com>
Date:   Sat Jan 25 22:33:06 2020 +0800

    fix bugs

[33mcommit 1397b9a28addf65c5765e75731a1562cb1ab604f[m
Author: Clouddoggo <jel.lim99@gmail.com>
Date:   Sat Jan 25 22:05:51 2020 +0800

    modify bye messgae

[33mcommit 8609f3aa58299112324def30343ffa9b8945e692[m
Author: Clouddoggo <jel.lim99@gmail.com>
Date:   Thu Jan 23 15:22:31 2020 +0800

    TextUITesting and modify bye message

[33mcommit 6de865234d9ab350df1bda3e466fa73fd158c2e9[m[33m ([m[1;33mtag: v6.1[m[33m)[m
Author: Clouddoggo <jel.lim99@gmail.com>
Date:   Wed Jan 22 23:17:30 2020 +0800

    Level-6

[33mcommit 1d087a4c9ffc9c887f8ea6c982543c390b198f52[m[33m ([m[1;33mtag: v6.0[m[33m)[m
Author: Clouddoggo <jel.lim99@gmail.com>
Date:   Wed Jan 22 22:55:50 2020 +0800

    Level-5 error handling

[33mcommit f0064e4e2a1fe9936b96fb5da920295baefe426d[m[33m ([m[1;33mtag: v5.0[m[33m)[m
Author: Clouddoggo <jel.lim99@gmail.com>
Date:   Wed Jan 22 13:01:11 2020 +0800

    Level-4

[33mcommit 7cbe9df6a6b3449e0698cbf23f719675e287811c[m[33m ([m[1;33mtag: v4.0[m[33m)[m
Author: Clouddoggo <jel.lim99@gmail.com>
Date:   Wed Jan 22 11:56:29 2020 +0800

    Level-3

[33mcommit 3b4b15841ad69ef935b5efd4610f87533fee1626[m[33m ([m[1;33mtag: v3.0[m[33m)[m
Author: Clouddoggo <jel.lim99@gmail.com>
Date:   Tue Jan 21 18:50:52 2020 +0800

    Level-2

[33mcommit 9937eb3e321d5de968f17f88aae473da4d71c271[m[33m ([m[1;33mtag: v2.1[m[33m)[m
Author: Clouddoggo <jel.lim99@gmail.com>
Date:   Tue Jan 21 18:46:57 2020 +0800

    Level-2

[33mcommit 99caf3801503b03b607244e8978b73614ba9c4c0[m[33m ([m[1;33mtag: v2.0[m[33m)[m
Author: Clouddoggo <jel.lim99@gmail.com>
Date:   Tue Jan 21 18:35:30 2020 +0800

    Level-1

[33mcommit 279a883165fa5d673ba20384e65ebe6251a228d3[m[33m ([m[1;33mtag: v1.0[m[33m)[m
Merge: fae28ab 30efbae
Author: Clouddoggo <jel.lim99@gmail.com>
Date:   Tue Jan 21 12:14:25 2020 +0800

    Merge branch 'gradle'

[33mcommit 30efbae968e44837d302132e20517f9ec713d01d[m[33m ([m[1;31morigin/gradle[m[33m, [m[1;32mgradle[m[33m)[m
Merge: 7b60e81 c4678f7
Author: Damith C. Rajapakse <damith@gmail.com>
Date:   Mon Oct 7 23:44:46 2019 +0800

    JavaFX tutorial: Support cross-platform JARs [#16]

[33mcommit c4678f7d8b2274a013b5a9c920fcfd6981f2122a[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Fri Sep 20 16:05:59 2019 +0800

    JavaFX tutorial: Support cross-platform JARs
    
    The OpenJFX plugin expects applications to be modular and bundled
    with jlink, resulting in fat jars that are not cross-platform. Let's
    manually include the required dependencies so that shadow can package
    them properly.

[33mcommit fae28ab89ea8846e56f2e6df33fbd6f9dd7fbea0[m
Merge: baa18a5 26a5013
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Sat Aug 31 16:43:08 2019 +0800

    Merge pull request #14 from j-lum/fxml-indent
    
    Change indentation in FXML samples to match AB-3

[33mcommit 26a5013d00ef2201cd6433f308f53dc52e277b76[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Sat Aug 31 16:40:20 2019 +0800

    Change indentation in FXML samples to match AB-3

[33mcommit 7b60e81e13738832f9c2e7e045987305bcd5e052[m
Merge: 6e6ace1 a3ca5a4
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Wed Aug 21 15:02:36 2019 +0800

    Merge pull request #13 from j-lum/javaexec
    
    Add configuration for console applications

[33mcommit a3ca5a4e3fe35013f5690cacd168b19f7370a013[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Wed Aug 21 00:32:40 2019 +0800

    Add configuration for console applications
    
    Gradle defaults to an empty stdin which results in runtime exceptions
    when attempting to read from `System.in`. Let's add some sensible
    defaults for students who may still need to work with the standard
    input stream.

[33mcommit 6e6ace130121eba10a3559454d8092300c7518d0[m
Merge: 0112efe cfd6da7
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Mon Aug 19 00:04:12 2019 +0800

    Merge pull request #12 from j-lum/gradle+x
    
    Change file mode on `gradle` to be executable (#9)

[33mcommit cfd6da79bab287c74d4b7f5666e61e686674fb29[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Sun Aug 18 23:25:53 2019 +0800

    Change file mode on `gradle` to be executable

[33mcommit baa18a5b60aee205f3593787bac4dc4a98a7687c[m
Merge: 999fa98 5df56a8
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Fri Aug 16 16:59:58 2019 +0800

    Merge pull request #8 from j-lum/tutorial-fix
    
    Remove references to DukeStub

[33mcommit 5df56a8908679dfc9efc6fcca655b1f9de85d51e[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Fri Aug 16 16:58:43 2019 +0800

    Remove references to DukeStub

[33mcommit 999fa98979ab5be8233b7838451650d8345bb520[m
Merge: d2ffa00 8d6d6f4
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Thu Aug 15 15:18:12 2019 +0800

    Merge pull request #7 from j-lum/tutorial-fix
    
    Address issues #5 and #6

[33mcommit 8d6d6f4b95dd028ee97ba3df4d36b38ffc92d343[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Thu Aug 15 14:05:46 2019 +0800

    Add images to JavaFX tutorial 3

[33mcommit b6df1a32827a0d318ba241a10bfded88101daca3[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Thu Aug 15 13:59:56 2019 +0800

    Fix typo referring to `HelloWorld`

[33mcommit d2ffa00dbe45016b1c087a9a26e2d5be1d595ee4[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Wed Aug 14 15:35:46 2019 +0800

    Update Gradle tutorial to reflect new checkstyle config

[33mcommit f20d61019a2ea5ce166440b47e9a37a8bd373bb0[m
Merge: 5c47c23 bad66fc
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Wed Aug 14 15:30:37 2019 +0800

    Merge pull request #3 from j-lum/inline-javafx-tutorials
    
    Inline JavaFX tutorials

[33mcommit bad66fc459bc2173e26be31598e04c4315df7933[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Mon Aug 12 14:24:27 2019 +0800

    Adapt JavaFX tutorial for Duke
    
    Rather than to have distinct tutorials building up to a Duke-like
    application, let's merge the tutorials into Duke's tutorials
    to enhance cohesiveness in the course material.
    
    We also merge the gradle/gradleless tutorials into one by providing a
    universal entry point to JavaFX in the style of AddressBook
    applications.
    
    Fix header levels in JavaFX Tutorial 1.
    
    Change code samples to use Duke.
    
    Add hints on required import statements.
    
    Add location hints to code snippets to help students find where to copy
    and paste them.
    
    Remove nitpicks to make the development process smoother.
    
    Fix usage of `Collections` to `FXCollections`.
    
    Replace image for JavaFX tutorial 3.
    
    Specify location to place images.
    
    Replace a screenshot that referred to the outdated package structure.
    
    Remove reference to DukeStub.

[33mcommit 0112efe4f745ecd1985e5362f85e8ddc5facb02c[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Mon Aug 12 18:09:24 2019 +0800

    Add sample checkstyle configuration
    
    Add toolVersion block in to Gradle code sample to prevent errors.

[33mcommit 5c47c238cef0369657cea1f3023dcc053aabe733[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Tue Aug 6 20:38:08 2019 +0800

    Specify checkstyle version
    
    Not specifying the checkstyle version causes it to fail with a cryptic
    error.

[33mcommit a2150c17028761c775b44368ede448060a7ce44e[m
Author: damithc <damith@gmail.com>
Date:   Tue Aug 6 20:10:10 2019 +0800

    textUiTestingTutorial.md: mention updating java/javac commands

[33mcommit 2d06f90400a17fd0ad95c0a68edf4917d12ee7ff[m
Author: damithc <damith@gmail.com>
Date:   Tue Aug 6 19:54:03 2019 +0800

    textUiTestingTutorial.md: add missing <br>

[33mcommit 5c491d3de4822b43f1db4c3bf806f81eae0c64a0[m
Author: damithc <damith@gmail.com>
Date:   Tue Aug 6 19:50:20 2019 +0800

    Add a tutorial on text UI testing

[33mcommit 65f72a8d7407cfc1d4ded97e87a8cbbbe6d16d8b[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Tue Aug 6 15:24:53 2019 +0800

    Add support for Gradle workflow

[33mcommit 53c04603712fd4132acd73091ffa37e29b7c0e70[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Tue Aug 6 15:15:04 2019 +0800

    Update list of contributors

[33mcommit 21af6a13f93c15964b20eb0ead90ef50ef70a42b[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Tue Aug 6 15:06:11 2019 +0800

    Fix typo in link to checkstyle config files

[33mcommit 8fe8afd9397509e450f821bc1f5524a3519ab488[m
Author: damithc <damith@gmail.com>
Date:   Mon Aug 5 19:29:40 2019 +0800

    README: use numbered list for steps

[33mcommit f20bff25cfb935b211b47aa3bcb5e690bbe614c0[m
Author: damithc <damith@gmail.com>
Date:   Mon Aug 5 19:26:04 2019 +0800

    Tweak tutorial text

[33mcommit 6a7120cbb6568134aef5d1d35e9596e4862acae4[m
Author: damithc <damith@gmail.com>
Date:   Mon Aug 5 19:08:53 2019 +0800

    Move Duke.java out of the package

[33mcommit 6bb6b9f6c525f5343fdc6dd3a43a086a11e9708e[m
Author: damithc <damith@gmail.com>
Date:   Mon Aug 5 19:08:08 2019 +0800

    .gitignore: add *.iml

[33mcommit 946f3bb509aa0710d45d820855ea3aa6cf096071[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Mon Jul 29 17:14:46 2019 +0800

    Import base branch for students to start off
    
    As student may not have learnt about branching, let's include a copy of
    the base branch into the main repository.

[33mcommit 245013d3ac98862fe7c5f52fee9a7b5c389fb7ab[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Mon Jul 29 17:14:46 2019 +0800

    Initial commit
