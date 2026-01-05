# 🐲 ghidra_scripts 🐲

A collection of scripts for [Ghidra](https://github.com/NationalSecurityAgency/ghidra)


---

## Scripts

<table>
  <thead>
  <tr>
    <th>Script</th>
    <th>Description</th>
    <th>Example Output</th>
  </tr>
  </thead>
  <tr>
    <td valign="top"><a href='https://github.com/kariemoorman/ghidra_scripts/blob/main/Export/ExportAllDecompiledFunctions.java' target='_blank'>ExportAllDecompiledFunctions</a></td>
    <td valign="top">Decompiles all functions in the currently loaded program, and exports the code to a text file.</td>
    <td valign="top">

```c

/* Decompiled output for program: SAMPLE.dll */
/* Total functions: 2056 */

/* ================================================== */
/* Function: GetVersionNumber */
/* Memory Address: 10001000 */
/* ================================================== */

undefined4 GetVersionNumber(void)

{
                    /* 0x1000  34  GetVersionNumber */
  return 0xf96;
}

```
      
  </td>
  </tr>
</table>

<br>

---

## Setup & Execution

### Step 1: Open Ghidra and Load a Program

1. Launch Ghidra and load a program (binary) you wish to analyze.
2. Make sure the **Decompiler** tool is enabled.

### Step 2: Add Script to Script Manager

1. Go to **Window > Script Manager** in Ghidra's main menu, and select **New Script**.
2. Ensure the script name matches the name of the Java class.
3. Copy-Paste the script, and Save.

### Step 3: Run the Script

1. In the **Script Manager** window, find the script.
2. Right-click on the script and select **Run**.


