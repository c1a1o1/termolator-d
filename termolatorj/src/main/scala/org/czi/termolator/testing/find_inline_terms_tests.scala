package org.czi.termolator.testing

import org.czi.termolator.porting.DataDef._
import org.czi.termolator.porting._

import scala.collection.immutable.Seq


/**
  *
  */
object find_inline_terms_tests extends App {

  import DataSet._

  term_utilities.ensureModuleInitialized()
  abbreviate.ensureModuleInitialized()
  find_terms.ensureModuleInitialized()
  dictionary.ensureModuleInitialized()

  dictionary.initialize_utilities()
  allFiles foreach { fileName ⇒
    test_get_topic_terms(fileName)
    test_find_inline_terms_for(fileName)
  }


  /**
    *
    */
  def test_get_topic_terms(fileName : String) = {

    val infile = new File[TXT](fileName+ ".txt")
    val txt2_file = new File[TXT2](fileName+ ".txt2")

    make_termolator_fact_txt_files.create_termolotator_fact_txt_files(
      input_file = infile,
      txt2_file,
      txt3_file = new File[TXT3](fileName + ".txt3"),
      fact_file = new File[FACT](fileName + ".fact"))

    val fact = new File[FACT](fileName + ".fact")
    val pos = new File[FACT](fileName + ".pos")
    run_adjust_missing_char_pos.fix_bad_char_in_file(
      fact,
      pos)

    run_adjust_missing_char_pos.fix_bad_char_in_file(fact, pos)

    val lines: Seq[str] = term_utilities.get_lines_from_file(txt2_file)

    //val text = lines.mkString("")
    val text = "Clinical practice guideline: hoarseness (dysphonia) This guideline provides evidence-based recommendations on managing hoarseness (dysphonia), defined as a disorder characterized by altered vocal quality, pitch, loudness, or vocal effort that impairs communication or reduces voice-related quality of life (QOL). Hoarseness affects nearly one-third of the population at some point in their lives. This guideline applies to all age groups evaluated in a setting where hoarseness would be identified or managed. It is intended for all clinicians who are likely to diagnose and manage patients with hoarseness. The primary purpose of this guideline is to improve diagnostic accuracy for hoarseness (dysphonia), reduce inappropriate antibiotic use, reduce inappropriate steroid use, reduce inappropriate use of anti-reflux medications, reduce inappropriate use of radiographic imaging, and promote appropriate use of laryngoscopy, voice therapy, and surgery. In creating this guideline the American Academy of Otolaryngology-Head and Neck Surgery Foundation selected a panel representing the fields of neurology, speech-language pathology, professional voice teaching, family medicine, pulmonology, geriatric medicine, nursing, internal medicine, otolaryngology-head and neck surgery, pediatrics, and consumers. The panel made strong recommendations that 1) the clinician should not routinely prescribe antibiotics to treat hoarseness and 2) the clinician should advocate voice therapy for patients diagnosed with hoarseness that reduces voice-related QOL. The panel made recommendations that 1) the clinician should diagnose hoarseness (dysphonia) in a patient with altered voice quality, pitch, loudness, or vocal effort that impairs communication or reduces voice-related QOL; 2) the clinician should assess the patient with hoarseness by history and/or physical examination for factors that modify management, such as one or more of the following: recent surgical procedures involving the neck or affecting the recurrent laryngeal nerve, recent endotracheal intubation, radiation treatment to the neck, a history of tobacco abuse, and occupation as a singer or vocal performer; 3) the clinician should visualize the patient's larynx, or refer the patient to a clinician who can visualize the larynx, when hoarseness fails to resolve by a maximum of three months after onset, or irrespective of duration if a serious underlying cause is suspected; 4) the clinician should not obtain computed tomography or magnetic resonance imaging of the patient with a primary complaint of hoarseness prior to visualizing the larynx; 5) the clinician should not prescribe anti-reflux medications for patients with hoarseness without signs or symptoms of gastroesophageal reflux disease; 6) the clinician should not routinely prescribe oral corticosteroids to treat hoarseness; 7) the clinician should visualize the larynx before prescribing voice therapy and document/communicate the results to the speech-language pathologist; and 8) the clinician should prescribe, or refer the patient to a clinician who can prescribe, botulinum toxin injections for the treatment of hoarseness caused by adductor spasmodic dysphonia. The panel offered as options that 1) the clinician may perform laryngoscopy at any time in a patient with hoarseness, or may refer the patient to a clinician who can visualize the larynx; 2) the clinician may prescribe anti-reflux medication for patients with hoarseness and signs of chronic laryngitis; and 3) the clinician may educate/counsel patients with hoarseness about control/preventive measures. This clinical practice guideline is not intended as a sole source of guidance in managing hoarseness (dysphonia). Rather, it is designed to assist clinicians by providing an evidence-based framework for decision-making strategies. The guideline is not intended to replace clinical judgment or establish a protocol for all individuals with this condition, and may not provide the only appropriate approach to diagnosing and managing this problem. "
    val result = inline_terms.get_topic_terms(text, offset = 0, filter_off = False)

    println("Result = ")
    result foreach println
  }


  /**
    *
    */
  def test_find_inline_terms_for(fileName : String) = {

    /**
      * @todo cannot assert anything about this becuase it is monolithical
      */
    find_terms.find_inline_terms_for(fileName, start = true)
  }
}