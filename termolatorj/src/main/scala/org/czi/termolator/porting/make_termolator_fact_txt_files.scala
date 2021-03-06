package org.czi.termolator.porting

import scala.collection.immutable.Seq

/**
  *
  */
object make_termolator_fact_txt_files extends JepEnabled {

  import DataDef._

  val moduleName = "make_termolator_fact_txt_files"

  /**
    * @code_reference [[./nyu-english-new/make_termolator_fact_txt_files.py:95]]
    */
  def main(input_file: File[File[TXT]], file_type: str)/*: Seq[(File[TXT2], File[TXT3], File[FACT])] */= {

    import scala.collection.JavaConverters._

    input_file.openText("r")

    if(input_file != null) {
      input_file.readlines().asScala map { f: str ⇒

        val file = new File[TXT](f)
        create_termolotator_fact_txt_files(file,
          new File[TXT2](s"${file.name}.txt2"),
          new File[TXT3](s"${file.name}.txt3"),
          new File[FACT](s"${file.name}.fact"))

      }
    }
    else
      Seq.empty
  }

  import term_utilities._


  /**
    * @code_reference [[./nyu-english-new/make_termolator_fact_txt_files.py:95]]
    *
    * @dataflow @todo mutate global state
    *          https://github.com/SemanticBeeng/The_Termolator/blob/ae8538bed1aa9c03d0a53db9b72228f6713c6c6b/make_termolator_fact_txt_files.py#L107-L107
    *
    * @dataflow @todo write paragraphs (?) to [[TXT2]], [[TXT3]]
    *          https://github.com/SemanticBeeng/The_Termolator/blob/07466eed8cc8be75f00cc81d60839fd873261078/make_termolator_fact_txt_files.py#L138-L138
    *
    *  @dataflow @todo write to [[FACT]]
    *          https://github.com/SemanticBeeng/The_Termolator/blob/a554407a4193b44fbbd7caead750362b2499a68b/make_termolator_fact_txt_files.py#L174-L174
    */
  def create_termolotator_fact_txt_files(input_file: File[TXT],
                                         txt2_file : File[TXT2], txt3_file: File[TXT3], fact_file: File[FACT]) = {

    FunctionDef("create_termolotator_fact_txt_files",
      ("input_file", input_file), ("txt2_file", txt2_file), ("txt3_file", txt3_file), ("fact_file", fact_file)).
      pyCall()

    (txt2_file, txt3_file, fact_file)

    // @todo impl
//    val lines = get_my_string_list
//
//    merge_multiline_and_fix_xml(lines)
//
//    remove_xml_spit_out_paragraph_start_end
//
//    modify_paragraph_delimiters
//    (txt2_file, txt3_file, fact_file)
  }

  /**
    * @code_reference [[./nyu-english-new/make_termolator_fact_txt_files.py:8]]
    */
  def modify_paragraph_delimiters = Unit

}
