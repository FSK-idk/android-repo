package co.feip.fefu2025.data.storage

import co.feip.fefu2025.R
import co.feip.fefu2025.data.storage.dto.RepoDto
import co.feip.fefu2025.data.storage.dto.RepoPageDto
import co.feip.fefu2025.data.storage.dto.LangDto
import kotlinx.datetime.LocalDate
import co.feip.fefu2025.Constants

class StabData {
    data class Data(
        val repoDto: RepoDto,
        val repoPageDto: RepoPageDto,
    )

    companion object {
        val stabs = listOf(
            Data(
                repoDto = RepoDto(
                    id = 0,
                    name = "Ferridex",
                    description = "Open Source AI Agent. We provide AI assistant that can helps you with your tasks.",
                    starNumber = 6809,
                    forkNumber = 752,
                    icon = R.drawable.ic_launcher_foreground,
                ),
                repoPageDto = RepoPageDto(
                    id = 0,
                    name = "Ferridex",
                    description = "Open Source AI Agent. We provide AI assistant that can helps you with your tasks.",
                    starNumber = 6809,
                    forkNumber = 752,
                    creationDate = LocalDate(2022, 8, 23),
                    langs = arrayOf(
                        LangDto(
                            "Python",
                            60f,
                            Constants.Companion.languageColor.getValue("Python")
                        ),
                        LangDto(
                            "JavaScript",
                            35f,
                            Constants.Companion.languageColor.getValue("JavaScript")
                        ),
                        LangDto(
                            "TypeScript",
                            5f,
                            Constants.Companion.languageColor.getValue("TypeScript")
                        ),
                    ),
                    icon = R.drawable.ic_launcher_foreground,
                )
            ),
            Data(
                repoDto = RepoDto(
                    id = 1,
                    name = "Butto",
                    description = "Super duper mega extra great editor written in JSON.",
                    starNumber = 26564,
                    forkNumber = 502,
                    icon = R.drawable.ic_launcher_foreground,
                ),
                repoPageDto = RepoPageDto(
                    id = 1,
                    name = "Butto",
                    description = "Super duper mega extra great editor written in JSON.",
                    starNumber = 26564,
                    forkNumber = 502,
                    creationDate = LocalDate(2012, 5, 12),
                    langs = arrayOf(
                        LangDto("JSON", 99f, Constants.Companion.languageColor.getValue("JSON")),
                        LangDto("Other", 1f, Constants.Companion.languageColor.getValue("Other")),
                    ),
                    icon = R.drawable.ic_launcher_foreground,
                )
            ),
            Data(
                repoDto = RepoDto(
                    id = 2,
                    name = "Arca",
                    description = "This is the beast convertor from PDF to PNG.",
                    starNumber = 26564,
                    forkNumber = 502,
                    icon = R.drawable.ic_launcher_foreground,
                ),
                repoPageDto = RepoPageDto(
                    id = 2,
                    name = "Arca",
                    description = "This is the beast convertor from PDF to PNG.",
                    starNumber = 26564,
                    forkNumber = 502,
                    creationDate = LocalDate(2015, 11, 21),
                    langs = arrayOf(
                        LangDto(
                            "Python",
                            87f,
                            Constants.Companion.languageColor.getValue("Python")
                        ),
                        LangDto("Go", 10f, Constants.Companion.languageColor.getValue("Go")),
                        LangDto("Shell", 2f, Constants.Companion.languageColor.getValue("Shell")),
                        LangDto("Lua", 1f, Constants.Companion.languageColor.getValue("Lua")),
                    ),
                    icon = R.drawable.ic_launcher_foreground,
                )
            ),
            Data(
                repoDto = RepoDto(
                    id = 3,
                    name = "Shifter",
                    description = "Some list of tech websites, search engines, haiku, QR-codes and more.",
                    starNumber = 182432,
                    forkNumber = 10244,
                    icon = R.drawable.ic_launcher_foreground,
                ),
                repoPageDto = RepoPageDto(
                    id = 3,
                    name = "Shifter",
                    description = "Some list of tech websites, search engines, haiku, QR-codes and more.",
                    starNumber = 182432,
                    forkNumber = 10244,
                    creationDate = LocalDate(2023, 1, 2),
                    langs = arrayOf(
                        LangDto("C++", 47f, Constants.Companion.languageColor.getValue("C++")),
                        LangDto(
                            "OpenCL",
                            13f,
                            Constants.Companion.languageColor.getValue("OpenCL")
                        ),
                        LangDto("CSS", 40f, Constants.Companion.languageColor.getValue("CSS")),
                    ),
                    icon = R.drawable.ic_launcher_foreground,
                )
            ),
            Data(
                repoDto = RepoDto(
                    id = 4,
                    name = "Sciptor",
                    description = "Build your biggest resume. We give you secure, portable, green, blooming something. Wanna try?",
                    starNumber = 1312,
                    forkNumber = 34,
                    icon = R.drawable.ic_launcher_foreground,
                ),
                repoPageDto = RepoPageDto(
                    id = 4,
                    name = "Sciptor",
                    description = "Build your biggest resume. We give you secure, portable, green, blooming something. Wanna try?",
                    starNumber = 1312,
                    forkNumber = 34,
                    creationDate = LocalDate(2020, 11, 12),
                    langs = arrayOf(
                        LangDto("Smarty", 7f, Constants.Companion.languageColor.getValue("Smarty")),
                        LangDto("Ruby", 17f, Constants.Companion.languageColor.getValue("Ruby")),
                        LangDto("SVG", 75f, Constants.Companion.languageColor.getValue("SVG")),
                        LangDto("Vue", 1f, Constants.Companion.languageColor.getValue("Vue")),
                    ),
                    icon = R.drawable.ic_launcher_foreground,
                )
            ),
            Data(
                repoDto = RepoDto(
                    id = 5,
                    name = "wiseCom",
                    description = "AI Notebook for your eyes. Do not look!",
                    starNumber = 12033,
                    forkNumber = 572,
                    icon = R.drawable.ic_launcher_foreground,
                ),
                repoPageDto = RepoPageDto(
                    id = 5,
                    name = "wiseCom",
                    description = "AI Notebook for your eyes. Do not look!",
                    starNumber = 12033,
                    forkNumber = 572,
                    creationDate = LocalDate(2024, 3, 22),
                    langs = arrayOf(
                        LangDto("XML", 10f, Constants.Companion.languageColor.getValue("XML")),
                        LangDto("Zig", 20f, Constants.Companion.languageColor.getValue("Zig")),
                        LangDto(
                            "Racket",
                            70f,
                            Constants.Companion.languageColor.getValue("Racket")
                        ),
                    ),
                    icon = R.drawable.ic_launcher_foreground,
                )
            ),
            Data(
                repoDto = RepoDto(
                    id = 6,
                    name = "Altica",
                    description = "Decode PNG to C++ code for free.",
                    starNumber = 233323,
                    forkNumber = 5412,
                    icon = R.drawable.ic_launcher_foreground,
                ),
                repoPageDto = RepoPageDto(
                    id = 6,
                    name = "Altica",
                    description = "Decode PNG to C++ code for free.",
                    starNumber = 233323,
                    forkNumber = 5412,
                    creationDate = LocalDate(2010, 8, 25),
                    langs = arrayOf(
                        LangDto("RON", 10f, Constants.Companion.languageColor.getValue("RON")),
                        LangDto(
                            "Prolog",
                            50f,
                            Constants.Companion.languageColor.getValue("Prolog")
                        ),
                        LangDto("Perl", 40f, Constants.Companion.languageColor.getValue("Perl")),
                    ),
                    icon = R.drawable.ic_launcher_foreground,
                )
            ),
            Data(
                repoDto = RepoDto(
                    id = 7,
                    name = "GATEs",
                    description = "Educational courses if you want.",
                    starNumber = 34121,
                    forkNumber = 233,
                    icon = R.drawable.ic_launcher_foreground,
                ),
                repoPageDto = RepoPageDto(
                    id = 7,
                    name = "GATEs",
                    description = "Educational courses if you want.",
                    starNumber = 34121,
                    forkNumber = 233,
                    creationDate = LocalDate(2013, 7, 3),
                    langs = arrayOf(
                        LangDto("Pyret", 70f, Constants.Companion.languageColor.getValue("Pyret")),
                        LangDto("RDoc", 10f, Constants.Companion.languageColor.getValue("RDoc")),
                        LangDto("Scala", 20f, Constants.Companion.languageColor.getValue("Scala")),
                    ),
                    icon = R.drawable.ic_launcher_foreground,
                )
            ),
            Data(
                repoDto = RepoDto(
                    id = 8,
                    name = "pAnDa",
                    description = "Interactive maps, guides and nothing more to save your time in the train.",
                    starNumber = 8933,
                    forkNumber = 582,
                    icon = R.drawable.ic_launcher_foreground,
                ),
                repoPageDto = RepoPageDto(
                    id = 8,
                    name = "pAnDa",
                    description = "Interactive maps, guides and nothing more to save your time in the train.",
                    starNumber = 8933,
                    forkNumber = 582,
                    creationDate = LocalDate(2016, 2, 23),
                    langs = arrayOf(
                        LangDto(
                            "SPARQL",
                            50f,
                            Constants.Companion.languageColor.getValue("SPARQL")
                        ),
                        LangDto(
                            "PostScript",
                            40f,
                            Constants.Companion.languageColor.getValue("PostScript")
                        ),
                        LangDto(
                            "Oxygene",
                            10f,
                            Constants.Companion.languageColor.getValue("Oxygene")
                        ),
                    ),
                    icon = R.drawable.ic_launcher_foreground,
                )
            ),
            Data(
                repoDto = RepoDto(
                    id = 9,
                    name = "Gidra",
                    description = "Collection of exploits.",
                    starNumber = 8933,
                    forkNumber = 582,
                    icon = R.drawable.ic_launcher_foreground,
                ),
                repoPageDto = RepoPageDto(
                    id = 9,
                    name = "Gidra",
                    description = "Collection of exploits.",
                    starNumber = 8933,
                    forkNumber = 582,
                    creationDate = LocalDate(2017, 9, 6),
                    langs = arrayOf(
                        LangDto("Nix", 30f, Constants.Companion.languageColor.getValue("Nix")),
                        LangDto("Metal", 30f, Constants.Companion.languageColor.getValue("Metal")),
                        LangDto("Lex", 40f, Constants.Companion.languageColor.getValue("Lex")),
                    ),
                    icon = R.drawable.ic_launcher_foreground,
                )
            ),
            Data(
                repoDto = RepoDto(
                    id = 10,
                    name = "dcknflcs",
                    description = "l;wjlfn lwei;ualn wefbh sf;web fpq[w [asasfasgpvreid.",
                    starNumber = 11111,
                    forkNumber = 0,
                    icon = R.drawable.ic_launcher_foreground,
                ),
                repoPageDto = RepoPageDto(
                    id = 10,
                    name = "dcknflcs",
                    description = "l;wjlfn lwei;ualn wefbh sf;web fpq[w [asasfasgpvreid.",
                    starNumber = 11111,
                    forkNumber = 0,
                    creationDate = LocalDate(2019, 4, 2),
                    langs = arrayOf(
                        LangDto(
                            "LiveScript",
                            50f,
                            Constants.Companion.languageColor.getValue("LiveScript")
                        ),
                        LangDto(
                            "Mercury",
                            30f,
                            Constants.Companion.languageColor.getValue("Mercury")
                        ),
                        LangDto(
                            "ObjectScript",
                            20f,
                            Constants.Companion.languageColor.getValue("ObjectScript")
                        ),
                    ),
                    icon = R.drawable.ic_launcher_foreground,
                )
            ),
            Data(
                repoDto = RepoDto(
                    id = 11,
                    name = "QBITCOIN",
                    description = "Overhyped coin that you don't have.",
                    starNumber = 624,
                    forkNumber = 12,
                    icon = R.drawable.ic_launcher_foreground,
                ),
                repoPageDto = RepoPageDto(
                    id = 11,
                    name = "QBITCOIN",
                    description = "Overhyped coin that you don't have.",
                    starNumber = 624,
                    forkNumber = 12,
                    creationDate = LocalDate(2011, 2, 5),
                    langs = arrayOf(
                        LangDto("Oz", 35f, Constants.Companion.languageColor.getValue("Oz")),
                        LangDto(
                            "Portugol",
                            20f,
                            Constants.Companion.languageColor.getValue("Portugol")
                        ),
                        LangDto("QML", 45f, Constants.Companion.languageColor.getValue("QML")),
                    ),
                    icon = R.drawable.ic_launcher_foreground,
                )
            ),
            Data(
                repoDto = RepoDto(
                    id = 12,
                    name = "downUp",
                    description = "Open-sourced music app for any repository.",
                    starNumber = 56321,
                    forkNumber = 314,
                    icon = R.drawable.ic_launcher_foreground,
                ),
                repoPageDto = RepoPageDto(
                    id = 12,
                    name = "downUp",
                    description = "Open-sourced music app for any repository.",
                    starNumber = 56321,
                    forkNumber = 314,
                    creationDate = LocalDate(2016, 12, 15),
                    langs = arrayOf(
                        LangDto("RBS", 12f, Constants.Companion.languageColor.getValue("RBS")),
                        LangDto("Self", 45f, Constants.Companion.languageColor.getValue("Self")),
                        LangDto("Slang", 43f, Constants.Companion.languageColor.getValue("Slang")),
                    ),
                    icon = R.drawable.ic_launcher_foreground,
                )
            ),
            Data(
                repoDto = RepoDto(
                    id = 13,
                    name = "MoriMori",
                    description = "TV platform with AI support. Combine your TV with AI.",
                    starNumber = 7231,
                    forkNumber = 122,
                    icon = R.drawable.ic_launcher_foreground,
                ),
                repoPageDto = RepoPageDto(
                    id = 13,
                    name = "MoriMori",
                    description = "TV platform with AI support. Combine your TV with AI.",
                    starNumber = 7231,
                    forkNumber = 122,
                    creationDate = LocalDate(2012, 5, 6),
                    langs = arrayOf(
                        LangDto("Slice", 13f, Constants.Companion.languageColor.getValue("Slice")),
                        LangDto("Sway", 44f, Constants.Companion.languageColor.getValue("Sway")),
                        LangDto(
                            "Textile",
                            43f,
                            Constants.Companion.languageColor.getValue("Textile")
                        ),
                    ),
                    icon = R.drawable.ic_launcher_foreground,
                )
            ),
            Data(
                repoDto = RepoDto(
                    id = 14,
                    name = "graphixxxx",
                    description = "Build RTS on digraphs.",
                    starNumber = 5621,
                    forkNumber = 326,
                    icon = R.drawable.ic_launcher_foreground,
                ),
                repoPageDto = RepoPageDto(
                    id = 14,
                    name = "graphixxxx",
                    description = "Build RTS on digraphs.",
                    starNumber = 5621,
                    forkNumber = 326,
                    creationDate = LocalDate(2010, 3, 18),
                    langs = arrayOf(
                        LangDto(
                            "Turing",
                            10f,
                            Constants.Companion.languageColor.getValue("Turing")
                        ),
                        LangDto(
                            "VBScript",
                            45f,
                            Constants.Companion.languageColor.getValue("VBScript")
                        ),
                        LangDto(
                            "Wollok",
                            45f,
                            Constants.Companion.languageColor.getValue("Wollok")
                        ),
                    ),
                    icon = R.drawable.ic_launcher_foreground,
                )
            ),
            Data(
                repoDto = RepoDto(
                    id = 15,
                    name = "awesome-cursors",
                    description = "Create nice cursor design.",
                    starNumber = 72531,
                    forkNumber = 8738,
                    icon = R.drawable.ic_launcher_foreground,
                ),
                repoPageDto = RepoPageDto(
                    id = 15,
                    name = "awesome-cursors",
                    description = "Create nice cursor design.",
                    starNumber = 72531,
                    forkNumber = 8738,
                    creationDate = LocalDate(2022, 2, 12),
                    langs = arrayOf(
                        LangDto("xBase", 50f, Constants.Companion.languageColor.getValue("xBase")),
                        LangDto("VHDL", 35f, Constants.Companion.languageColor.getValue("VHDL")),
                        LangDto("Swift", 15f, Constants.Companion.languageColor.getValue("Swift")),
                    ),
                    icon = R.drawable.ic_launcher_foreground,
                )
            ),
            Data(
                repoDto = RepoDto(
                    id = 16,
                    name = "karpStudio",
                    description = "A collection of QWERTY servers.",
                    starNumber = 5112,
                    forkNumber = 122,
                    icon = R.drawable.ic_launcher_foreground,
                ),
                repoPageDto = RepoPageDto(
                    id = 16,
                    name = "karpStudio",
                    description = "A collection of QWERTY servers.",
                    starNumber = 5112,
                    forkNumber = 122,
                    creationDate = LocalDate(2012, 5, 19),
                    langs = arrayOf(
                        LangDto(
                            "Thrift",
                            50f,
                            Constants.Companion.languageColor.getValue("Thrift")
                        ),
                        LangDto("Raku", 35f, Constants.Companion.languageColor.getValue("Raku")),
                        LangDto("PLSQL", 15f, Constants.Companion.languageColor.getValue("PLSQL")),
                    ),
                    icon = R.drawable.ic_launcher_foreground,
                )
            )
        )

        val stabsRand = List<Data>(40) { index -> stabs.random() }
    }
}