//Export all decompiled functions to a txt file

// @author Karie Moorman
// @category Export
// @keybinding
// @menupath
// @toolbar
// @runtime Java

import ghidra.app.decompiler.*;
import ghidra.util.task.ConsoleTaskMonitor;
import ghidra.program.model.listing.*;
import ghidra.program.model.address.*;
import ghidra.app.script.GhidraScript;
import java.io.*;

public class ExportAllDecompiledFunctions extends GhidraScript {

    @Override
    public void run() throws Exception {
        // Initialize decompiler interface
        DecompInterface decompiler = new DecompInterface();
        decompiler.openProgram(currentProgram);

        // Get all functions in the program
        FunctionManager functionManager = currentProgram.getFunctionManager();
        FunctionIterator functions = functionManager.getFunctions(true);

        // Ask user for output directory
        File outputDir = askDirectory("Select Output Folder", "OK");
        File outputFile = new File(outputDir, currentProgram.getName() + "_decompiled.txt");

        // Prepare output file for writing
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write("/* Decompiled output for program: " + currentProgram.getName() + " */\n");
            writer.write("/* Total functions: " + functionManager.getFunctionCount() + " */\n\n");

            // Iterate over all functions and decompile them
            while (functions.hasNext()) {
                Function function = functions.next();
                try {
                    // Decompile function (allow up to 60 seconds)
                    DecompileResults result = decompiler.decompileFunction(function, 60, ConsoleTaskMonitor.DUMMY);

                    // Write function details
                    writer.write("/* ================================================== */\n");
                    writer.write("/* Function: " + function.getName() + " */\n");
                    writer.write("/* Memory Address: " + function.getEntryPoint() + " */\n");
                    writer.write("/* ================================================== */\n");

                    // If decompilation successful, write code
                    if (result != null && result.decompileCompleted()) {
                        writer.write(result.getDecompiledFunction().getC());
                        writer.write("\n\n");
                    } else {
                        writer.write("/* Failed to decompile: " + function.getName() + " */\n\n");
                    }
                } catch (Exception e) {
                    writer.write("/* Error decompiling " + function.getName() + ": " + e.getMessage() + " */\n\n");
                }
            }
        } catch (IOException e) {
            println("Error writing to file: " + e.getMessage());
        }

        println("All functions exported to: " + outputFile.getAbsolutePath());
    }
}

